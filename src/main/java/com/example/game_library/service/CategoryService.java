package com.example.game_library.service;

import com.example.game_library.dto.CategoryDto;
import com.example.game_library.entity.Category;
import com.example.game_library.mapper.CategoryMapper;
import com.example.game_library.repo.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> findAllDto() {
        log.info("Fetching all categories");
        List<CategoryDto> dtos = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} categories", dtos.size());
        return dtos;
    }

    public CategoryDto findByIdDto(Long id) {
        log.info("Fetching category by id={}", id);
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Category with id={} not found", id);
                    return null;
                });
    }

    public CategoryDto saveDto(CategoryDto categoryDto) {
        log.info("Saving category: {}", categoryDto);
        Category saved = categoryRepository.save(categoryMapper.toEntity(categoryDto));
        log.info("Saved category with id={}", saved.getId());
        return categoryMapper.toDto(saved);
    }

    public CategoryDto updateDto(Long id, CategoryDto categoryDto) {
        log.info("Updating category with id={}", id);
        return categoryRepository.findById(id)
                .map(existing -> {
                    categoryMapper.updateEntityFromDto(categoryDto, existing);
                    Category updated = categoryRepository.save(existing);
                    log.info("Updated category with id={}", updated.getId());
                    return categoryMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Category with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting category with id={}", id);
        categoryRepository.deleteById(id);
        log.info("Deleted category with id={}", id);
    }
}
