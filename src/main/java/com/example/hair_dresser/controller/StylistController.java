package com.example.hair_dresser.controller;

import com.example.hair_dresser.dto.StylistDto;
import com.example.hair_dresser.service.StylistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Stylist Controller", description = "API для управления издателями")
@Validated
@RestController
@RequestMapping("/api/stylists")
public class StylistController {

    private final StylistService stylistService;

    public StylistController(StylistService stylistService) {
        this.stylistService = stylistService;
    }

    @Operation(summary = "Получить список всех стилистов")
    @GetMapping
    public ResponseEntity<List<StylistDto>> getAll() {
        List<StylistDto> stylists = stylistService.findAllDto();
        return ResponseEntity.ok(stylists);
    }

    @Operation(summary = "Получить стилиста по ID")
    @GetMapping("/{id}")
    public ResponseEntity<StylistDto> getById(@PathVariable Long id) {
        StylistDto stylist = stylistService.findByIdDto(id);
        return ResponseEntity.ok(stylist);
    }

    @Operation(summary = "Создать нового стилиста")
    @PostMapping
    public ResponseEntity<StylistDto> create(@Valid @RequestBody StylistDto stylistDto) {
        StylistDto created = stylistService.saveDto(stylistDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить стилиста по ID")
    @PutMapping("/{id}")
    public ResponseEntity<StylistDto> update(@PathVariable Long id, @Valid @RequestBody StylistDto stylistDto) {
        StylistDto updated = stylistService.updateDto(id, stylistDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить стилиста по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stylistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
