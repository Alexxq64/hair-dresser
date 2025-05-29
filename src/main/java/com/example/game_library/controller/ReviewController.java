package com.example.game_library.controller;

import com.example.game_library.dto.ReviewDto;
import com.example.game_library.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review Controller", description = "API для управления отзывами")
@Validated
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Получить список всех отзывов")
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        List<ReviewDto> reviews = reviewService.findAllDto();
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "Получить отзыв по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable Long id) {
        ReviewDto review = reviewService.findByIdDto(id);
        return ResponseEntity.ok(review);
    }

    @Operation(summary = "Создать новый отзыв")
    @PostMapping
    public ResponseEntity<ReviewDto> create(@Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto created = reviewService.saveDto(reviewDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить отзыв по ID")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto updated = reviewService.updateDto(id, reviewDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить отзыв по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
