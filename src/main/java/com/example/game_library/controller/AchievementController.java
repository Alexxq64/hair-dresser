package com.example.game_library.controller;

import com.example.game_library.dto.AchievementDto;
import com.example.game_library.service.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Achievement Controller", description = "API для управления достижениями")
@Validated
@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Operation(summary = "Получить список всех достижений")
    @GetMapping
    public ResponseEntity<List<AchievementDto>> getAll() {
        List<AchievementDto> achievements = achievementService.findAllDto();
        return ResponseEntity.ok(achievements);
    }

    @Operation(summary = "Получить достижение по ID")
    @GetMapping("/{id}")
    public ResponseEntity<AchievementDto> getById(@PathVariable Long id) {
        AchievementDto achievement = achievementService.findByIdDto(id);
        return ResponseEntity.ok(achievement);
    }

    @Operation(summary = "Создать новое достижение")
    @PostMapping
    public ResponseEntity<AchievementDto> create(@Valid @RequestBody AchievementDto achievementDto) {
        AchievementDto created = achievementService.saveDto(achievementDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить достижение по ID")
    @PutMapping("/{id}")
    public ResponseEntity<AchievementDto> update(@PathVariable Long id, @Valid @RequestBody AchievementDto achievementDto) {
        AchievementDto updated = achievementService.updateDto(id, achievementDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить достижение по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
