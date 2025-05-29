package com.example.game_library.service;

import com.example.game_library.dto.ReviewDto;
import com.example.game_library.entity.Review;
import com.example.game_library.mapper.ReviewMapper;
import com.example.game_library.repo.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDto> findAllDto() {
        log.info("Fetching all reviews");
        List<ReviewDto> reviews = reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} reviews", reviews.size());
        return reviews;
    }

    public ReviewDto findByIdDto(Long id) {
        log.info("Fetching review by id={}", id);
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Review with id={} not found", id);
                    return null;
                });
    }

    public ReviewDto saveDto(ReviewDto reviewDto) {
        log.info("Saving new review: {}", reviewDto);
        Review saved = reviewRepository.save(reviewMapper.toEntity(reviewDto));
        log.info("Saved review with id={}", saved.getId());
        return reviewMapper.toDto(saved);
    }

    public ReviewDto updateDto(Long id, ReviewDto reviewDto) {
        log.info("Updating review with id={}", id);
        return reviewRepository.findById(id)
                .map(existing -> {
                    reviewMapper.updateEntityFromDto(reviewDto, existing);
                    Review updated = reviewRepository.save(existing);
                    log.info("Updated review with id={}", updated.getId());
                    return reviewMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Review with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting review with id={}", id);
        reviewRepository.deleteById(id);
        log.info("Deleted review with id={}", id);
    }
}
