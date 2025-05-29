package com.example.game_library.controller;

import com.example.game_library.dto.CategoryDto;
import com.example.game_library.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Controller", description = "API для управления категориями")
@Validated
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Получить список всех категорий")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categories = categoryService.findAllDto();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Получить категорию по ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        CategoryDto category = categoryService.findByIdDto(id);
        return ResponseEntity.ok(category);
    }

    @Operation(summary = "Создать новую категорию")
    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.saveDto(categoryDto);
        return ResponseEntity.ok(createdCategory);
    }

    @Operation(summary = "Обновить категорию по ID")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateDto(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @Operation(summary = "Удалить категорию по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
