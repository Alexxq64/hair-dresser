package com.example.hair_dresser.controller;

import com.example.hair_dresser.dto.FeedbackDto;
import com.example.hair_dresser.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Feedback Controller", description = "API для управления отзывами")
@Validated
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Operation(summary = "Получить список всех отзывов")
    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAll() {
        List<FeedbackDto> feedbacks = feedbackService.findAllDto();
        return ResponseEntity.ok(feedbacks);
    }

    @Operation(summary = "Получить отзыв по ID")
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getById(@PathVariable Long id) {
        FeedbackDto feedback = feedbackService.findByIdDto(id);
        return ResponseEntity.ok(feedback);
    }

    @Operation(summary = "Создать новый отзыв")
    @PostMapping
    public ResponseEntity<FeedbackDto> create(@Valid @RequestBody FeedbackDto feedbackDto) {
        FeedbackDto created = feedbackService.saveDto(feedbackDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить отзыв по ID")
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> update(@PathVariable Long id, @Valid @RequestBody FeedbackDto feedbackDto) {
        FeedbackDto updated = feedbackService.updateDto(id, feedbackDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить отзыв по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
