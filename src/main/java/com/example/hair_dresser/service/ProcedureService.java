package com.example.hair_dresser.service;

import com.example.hair_dresser.dto.ProcedureDto;
import com.example.hair_dresser.entity.Category;
import com.example.hair_dresser.entity.Procedure;
import com.example.hair_dresser.mapper.ProcedureMapper;
import com.example.hair_dresser.repo.CategoryRepository;
import com.example.hair_dresser.repo.ProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

    private static final Logger log = LoggerFactory.getLogger(ProcedureService.class);

    private final ProcedureRepository procedureRepository;
    private final CategoryRepository categoryRepository;
    private final ProcedureMapper procedureMapper;

    public ProcedureService(
            ProcedureRepository procedureRepository,
            CategoryRepository categoryRepository,
            ProcedureMapper procedureMapper
    ) {
        this.procedureRepository = procedureRepository;
        this.categoryRepository = categoryRepository;
        this.procedureMapper = procedureMapper;
    }

    public List<ProcedureDto> findAllDto() {
        log.info("Fetching all procedures");
        List<ProcedureDto> dtos = procedureRepository.findAll().stream()
                .map(procedureMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} procedures", dtos.size());
        return dtos;
    }

    public ProcedureDto findByIdDto(Long id) {
        log.info("Fetching procedure by id={}", id);
        return procedureRepository.findById(id)
                .map(procedureMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Procedure with id={} not found", id);
                    return new RuntimeException("Процедура с id " + id + " не найдена");
                });
    }

    public ProcedureDto saveDto(ProcedureDto dto) {
        log.info("Saving procedure: {}", dto);

        Procedure entity = procedureMapper.toEntity(dto);

        // Обработка категорий
        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            Set<Long> categoryIds = dto.getCategories().stream()
                    .map(c -> c.getId())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            List<Category> categories = categoryRepository.findAllById(categoryIds);
            categories.forEach(entity::addCategory);
        }

        Procedure saved = procedureRepository.save(entity);
        log.info("Saved procedure with id={}", saved.getId());
        return procedureMapper.toDto(saved);
    }

    public ProcedureDto updateDto(Long id, ProcedureDto dto) {
        log.info("Updating procedure with id={}", id);

        Procedure existing = procedureRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Procedure with id={} not found for update", id);
                    return new RuntimeException("Процедура с id " + id + " не найдена");
                });

        procedureMapper.updateEntityFromDto(dto, existing);

        // Обновление категорий
        existing.getCategories().clear(); // удаляем старые
        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            Set<Long> categoryIds = dto.getCategories().stream()
                    .map(c -> c.getId())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            List<Category> categories = categoryRepository.findAllById(categoryIds);
            categories.forEach(existing::addCategory);
        }

        Procedure updated = procedureRepository.save(existing);
        log.info("Updated procedure with id={}", updated.getId());
        return procedureMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        log.info("Deleting procedure with id={}", id);
        procedureRepository.deleteById(id);
        log.info("Deleted procedure with id={}", id);
    }
}
