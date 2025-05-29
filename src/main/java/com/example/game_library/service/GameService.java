package com.example.game_library.service;

import com.example.game_library.dto.GameDto;
import com.example.game_library.entity.Game;
import com.example.game_library.mapper.GameMapper;
import com.example.game_library.repo.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    // DTO методы
    public List<GameDto> findAllDto() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    public GameDto findByIdDto(Long id) {
        return gameRepository.findById(id)
                .map(gameMapper::toDto)
                .orElse(null);
    }

    public GameDto saveDto(GameDto gameDto) {
        Game saved = gameRepository.save(gameMapper.toEntity(gameDto));
        return gameMapper.toDto(saved);
    }

    public GameDto updateDto(Long id, GameDto gameDto) {
        return gameRepository.findById(id)
                .map(existing -> {
                    gameMapper.updateEntityFromDto(gameDto, existing);
                    Game updated = gameRepository.save(existing);
                    return gameMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    // Entity-методы (оставляем на всякий случай)
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
