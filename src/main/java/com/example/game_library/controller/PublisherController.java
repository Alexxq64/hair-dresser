package com.example.game_library.controller;

import com.example.game_library.dto.PublisherDto;
import com.example.game_library.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Publisher Controller", description = "API для управления издателями")
@Validated
@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Operation(summary = "Получить список всех издателей")
    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll() {
        List<PublisherDto> publishers = publisherService.findAllDto();
        return ResponseEntity.ok(publishers);
    }

    @Operation(summary = "Получить издателя по ID")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable Long id) {
        PublisherDto publisher = publisherService.findByIdDto(id);
        return ResponseEntity.ok(publisher);
    }

    @Operation(summary = "Создать нового издателя")
    @PostMapping
    public ResponseEntity<PublisherDto> create(@Valid @RequestBody PublisherDto publisherDto) {
        PublisherDto created = publisherService.saveDto(publisherDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить издателя по ID")
    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> update(@PathVariable Long id, @Valid @RequestBody PublisherDto publisherDto) {
        PublisherDto updated = publisherService.updateDto(id, publisherDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить издателя по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
