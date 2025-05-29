package com.example.game_library.service;

import com.example.game_library.dto.PlayerDto;
import com.example.game_library.entity.Player;
import com.example.game_library.mapper.PlayerMapper;
import com.example.game_library.repo.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDto> findAllDto() {
        log.info("Fetching all players");
        List<PlayerDto> dtos = playerRepository.findAll()
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} players", dtos.size());
        return dtos;
    }

    public PlayerDto findByIdDto(Long id) {
        log.info("Fetching player by id={}", id);
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Player with id={} not found", id);
                    return null;
                });
    }

    public PlayerDto saveDto(PlayerDto playerDto) {
        log.info("Saving player: {}", playerDto);
        Player saved = playerRepository.save(playerMapper.toEntity(playerDto));
        log.info("Saved player with id={}", saved.getId());
        return playerMapper.toDto(saved);
    }

    public PlayerDto updateDto(Long id, PlayerDto playerDto) {
        log.info("Updating player with id={}", id);
        return playerRepository.findById(id)
                .map(existing -> {
                    playerMapper.updateEntityFromDto(playerDto, existing);
                    Player updated = playerRepository.save(existing);
                    log.info("Updated player with id={}", updated.getId());
                    return playerMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Player with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting player with id={}", id);
        playerRepository.deleteById(id);
        log.info("Deleted player with id={}", id);
    }

    // Старые entity-методы (если нужны)
    public List<Player> findAll() {
        log.info("Fetching all player entities");
        return playerRepository.findAll();
    }

    public Player findById(Long id) {
        log.info("Fetching player entity by id={}", id);
        return playerRepository.findById(id).orElse(null);
    }

    public Player save(Player player) {
        log.info("Saving player entity: {}", player);
        Player saved = playerRepository.save(player);
        log.info("Saved player entity with id={}", saved.getId());
        return saved;
    }
}
