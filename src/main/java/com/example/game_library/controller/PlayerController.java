package com.example.game_library.controller;

import com.example.game_library.dto.PlayerDto;
import com.example.game_library.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDto> getAll() {
        return playerService.findAllDto();
    }

    @GetMapping("/{id}")
    public PlayerDto getById(@PathVariable Long id) {
        return playerService.findByIdDto(id);
    }

    @PostMapping
    public PlayerDto create(@Valid @RequestBody PlayerDto playerDto) {
        return playerService.saveDto(playerDto);
    }

    @PutMapping("/{id}")
    public PlayerDto update(@PathVariable Long id, @Valid @RequestBody PlayerDto playerDto) {
        return playerService.updateDto(id, playerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.deleteById(id);
    }
}
