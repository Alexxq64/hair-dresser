package com.example.hair_dresser.controller;

import com.example.hair_dresser.dto.VisitDto;
import com.example.hair_dresser.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Visit Controller", description = "API для управления визитами")
@Validated
@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @Operation(summary = "Получить список всех визитов")
    @GetMapping
    public ResponseEntity<List<VisitDto>> getAll() {
        List<VisitDto> visits = visitService.findAllDto();
        return ResponseEntity.ok(visits);
    }

    @Operation(summary = "Получить визит по ID")
    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> getById(@PathVariable Long id) {
        VisitDto visit = visitService.findByIdDto(id);
        return ResponseEntity.ok(visit);
    }

    @Operation(summary = "Создать новый визит")
    @PostMapping
    public ResponseEntity<VisitDto> create(@Valid @RequestBody VisitDto visitDto) {
        VisitDto created = visitService.saveDto(visitDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить визит по ID")
    @PutMapping("/{id}")
    public ResponseEntity<VisitDto> update(@PathVariable Long id, @Valid @RequestBody VisitDto visitDto) {
        VisitDto updated = visitService.updateDto(id, visitDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить визит по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
