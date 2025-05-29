package com.example.game_library.controller;

import com.example.game_library.dto.GameDto;
import com.example.game_library.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Game Controller", description = "API для управления играми")
@Validated
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Получить список всех игр")
    @GetMapping
    public ResponseEntity<List<GameDto>> getAll() {
        return ResponseEntity.ok(gameService.findAllDto());
    }

    @Operation(summary = "Получить игру по ID")
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getById(@PathVariable Long id) {
        GameDto game = gameService.findByIdDto(id);
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Создать новую игру")
    @PostMapping
    public ResponseEntity<GameDto> create(@Valid @RequestBody GameDto gameDto) {
        GameDto created = gameService.saveDto(gameDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить игру по ID")
    @PutMapping("/{id}")
    public ResponseEntity<GameDto> update(@PathVariable Long id, @Valid @RequestBody GameDto gameDto) {
        GameDto updated = gameService.updateDto(id, gameDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить игру по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
