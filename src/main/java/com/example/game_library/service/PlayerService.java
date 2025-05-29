package com.example.game_library.service;

import com.example.game_library.dto.PlayerDto;
import com.example.game_library.entity.Player;
import com.example.game_library.mapper.PlayerMapper;
import com.example.game_library.repo.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDto> findAllDto() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    public PlayerDto findByIdDto(Long id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElse(null);
    }

    public PlayerDto saveDto(PlayerDto playerDto) {
        Player saved = playerRepository.save(playerMapper.toEntity(playerDto));
        return playerMapper.toDto(saved);
    }

    public PlayerDto updateDto(Long id, PlayerDto playerDto) {
        return playerRepository.findById(id)
                .map(existing -> {
                    playerMapper.updateEntityFromDto(playerDto, existing);
                    Player updated = playerRepository.save(existing);
                    return playerMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    // Старые entity-методы (если нужны)
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
