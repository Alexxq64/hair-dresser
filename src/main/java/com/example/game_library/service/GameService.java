package com.example.game_library.service;

import com.example.game_library.dto.GameDto;
import com.example.game_library.entity.Game;
import com.example.game_library.mapper.GameMapper;
import com.example.game_library.repo.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    public List<GameDto> findAllDto() {
        log.info("Fetching all games");
        List<GameDto> dtos = gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} games", dtos.size());
        return dtos;
    }

    public GameDto findByIdDto(Long id) {
        log.info("Fetching game by id={}", id);
        return gameRepository.findById(id)
                .map(gameMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Game with id={} not found", id);
                    return new RuntimeException("Игра с id " + id + " не найдена");
                });
    }

    public GameDto saveDto(GameDto gameDto) {
        log.info("Saving game: {}", gameDto);
        Game saved = gameRepository.save(gameMapper.toEntity(gameDto));
        log.info("Saved game with id={}", saved.getId());
        return gameMapper.toDto(saved);
    }

    public GameDto updateDto(Long id, GameDto gameDto) {
        log.info("Updating game with id={}", id);
        Game existing = gameRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Game with id={} not found for update", id);
                    return new RuntimeException("Игра с id " + id + " не найдена");
                });

        gameMapper.updateEntityFromDto(gameDto, existing);
        Game updated = gameRepository.save(existing);
        log.info("Updated game with id={}", updated.getId());
        return gameMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        log.info("Deleting game with id={}", id);
        gameRepository.deleteById(id);
        log.info("Deleted game with id={}", id);
    }
}
