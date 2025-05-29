package com.example.game_library.service;

import com.example.game_library.dto.PublisherDto;
import com.example.game_library.entity.Publisher;
import com.example.game_library.mapper.PublisherMapper;
import com.example.game_library.repo.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private static final Logger log = LoggerFactory.getLogger(PublisherService.class);

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public List<PublisherDto> findAllDto() {
        log.info("Fetching all publishers");
        List<PublisherDto> dtos = publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} publishers", dtos.size());
        return dtos;
    }

    public PublisherDto findByIdDto(Long id) {
        log.info("Fetching publisher by id={}", id);
        return publisherRepository.findById(id)
                .map(publisherMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Publisher with id={} not found", id);
                    return null;
                });
    }

    public PublisherDto saveDto(PublisherDto publisherDto) {
        log.info("Saving publisher: {}", publisherDto);
        Publisher saved = publisherRepository.save(publisherMapper.toEntity(publisherDto));
        log.info("Saved publisher with id={}", saved.getId());
        return publisherMapper.toDto(saved);
    }

    public PublisherDto updateDto(Long id, PublisherDto publisherDto) {
        log.info("Updating publisher with id={}", id);
        return publisherRepository.findById(id)
                .map(existing -> {
                    publisherMapper.updateEntityFromDto(publisherDto, existing);
                    Publisher updated = publisherRepository.save(existing);
                    log.info("Updated publisher with id={}", updated.getId());
                    return publisherMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Publisher with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting publisher with id={}", id);
        publisherRepository.deleteById(id);
        log.info("Deleted publisher with id={}", id);
    }
}
