package com.example.hair_dresser.controller;

import com.example.hair_dresser.dto.ProcedureDto;
import com.example.hair_dresser.service.ProcedureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Procedure Controller", description = "API для управления процедурами")
@Validated
@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Operation(summary = "Получить список всех процедур")
    @GetMapping
    public ResponseEntity<List<ProcedureDto>> getAll() {
        return ResponseEntity.ok(procedureService.findAllDto());
    }

    @Operation(summary = "Получить процедуру по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDto> getById(@PathVariable Long id) {
        ProcedureDto procedure = procedureService.findByIdDto(id);
        return ResponseEntity.ok(procedure);
    }

    @Operation(summary = "Создать новую процедуру")
    @PostMapping
    public ResponseEntity<ProcedureDto> create(@Valid @RequestBody ProcedureDto procedureDto) {
        ProcedureDto created = procedureService.saveDto(procedureDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить процедуру по ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDto> update(@PathVariable Long id, @Valid @RequestBody ProcedureDto procedureDto) {
        ProcedureDto updated = procedureService.updateDto(id, procedureDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить процедуру по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        procedureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
