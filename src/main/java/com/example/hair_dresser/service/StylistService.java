package com.example.hair_dresser.service;

import com.example.hair_dresser.dto.StylistDto;
import com.example.hair_dresser.entity.Stylist;
import com.example.hair_dresser.mapper.StylistMapper;
import com.example.hair_dresser.repo.StylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StylistService {

    private static final Logger log = LoggerFactory.getLogger(StylistService.class);

    private final StylistRepository stylistRepository;
    private final StylistMapper stylistMapper;

    public StylistService(StylistRepository stylistRepository, StylistMapper stylistMapper) {
        this.stylistRepository = stylistRepository;
        this.stylistMapper = stylistMapper;
    }

    public List<StylistDto> findAllDto() {
        log.info("Fetching all stylists");
        List<StylistDto> dtos = stylistRepository.findAll()
                .stream()
                .map(stylistMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} stylists", dtos.size());
        return dtos;
    }

    public StylistDto findByIdDto(Long id) {
        log.info("Fetching stylist by id={}", id);
        return stylistRepository.findById(id)
                .map(stylistMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Stylist with id={} not found", id);
                    return new RuntimeException("Стилист с id " + id + " не найден");
                });
    }

    public StylistDto saveDto(StylistDto stylistDto) {
        log.info("Saving stylist: {}", stylistDto);
        Stylist saved = stylistRepository.save(stylistMapper.toEntity(stylistDto));
        log.debug("Saved stylist: {}", saved);
        return stylistMapper.toDto(saved);
    }

    public StylistDto updateDto(Long id, StylistDto stylistDto) {
        log.info("Updating stylist with id={}", id);
        Stylist existing = stylistRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Stylist with id={} not found for update", id);
                    return new RuntimeException("Стилист с id " + id + " не найден");
                });

        stylistMapper.updateEntityFromDto(stylistDto, existing);
        Stylist updated = stylistRepository.save(existing);
        log.debug("Updated stylist: {}", updated);
        return stylistMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        log.info("Deleting stylist with id={}", id);
        if (!stylistRepository.existsById(id)) {
            log.warn("Stylist with id={} not found for deletion", id);
            throw new RuntimeException("Стилист с id " + id + " не найден");
        }
        stylistRepository.deleteById(id);
        log.debug("Deleted stylist with id={}", id);
    }
}
