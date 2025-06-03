package com.example.hair_dresser.service;

import com.example.hair_dresser.dto.FeedbackDto;
import com.example.hair_dresser.entity.Feedback;
import com.example.hair_dresser.mapper.FeedbackMapper;
import com.example.hair_dresser.repo.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    public List<FeedbackDto> findAllDto() {
        log.info("Fetching all feedbacks");
        List<FeedbackDto> feedbacks = feedbackRepository.findAll()
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} feedbacks", feedbacks.size());
        return feedbacks;
    }

    public FeedbackDto findByIdDto(Long id) {
        log.info("Fetching feedback by id={}", id);
        return feedbackRepository.findById(id)
                .map(feedbackMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Feedback with id={} not found", id);
                    return null;
                });
    }

    public FeedbackDto saveDto(FeedbackDto feedbackDto) {
        log.info("Saving new feedback: {}", feedbackDto);
        Feedback saved = feedbackRepository.save(feedbackMapper.toEntity(feedbackDto));
        log.info("Saved feedback with id={}", saved.getId());
        return feedbackMapper.toDto(saved);
    }

    public FeedbackDto updateDto(Long id, FeedbackDto feedbackDto) {
        log.info("Updating feedback with id={}", id);
        return feedbackRepository.findById(id)
                .map(existing -> {
                    feedbackMapper.updateEntityFromDto(feedbackDto, existing);
                    Feedback updated = feedbackRepository.save(existing);
                    log.info("Updated feedback with id={}", updated.getId());
                    return feedbackMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Feedback with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting feedback with id={}", id);
        feedbackRepository.deleteById(id);
        log.info("Deleted feedback with id={}", id);
    }
}
