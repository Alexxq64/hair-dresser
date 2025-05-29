package com.example.game_library.service;

import com.example.game_library.dto.AchievementDto;
import com.example.game_library.entity.Achievement;
import com.example.game_library.entity.Player;
import com.example.game_library.mapper.AchievementMapper;
import com.example.game_library.repo.AchievementRepository;
import com.example.game_library.repo.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    private static final Logger log = LoggerFactory.getLogger(AchievementService.class);

    private final AchievementRepository achievementRepository;
    private final PlayerRepository playerRepository;
    private final AchievementMapper achievementMapper;

    public AchievementService(
            AchievementRepository achievementRepository,
            PlayerRepository playerRepository,
            AchievementMapper achievementMapper
    ) {
        this.achievementRepository = achievementRepository;
        this.playerRepository = playerRepository;
        this.achievementMapper = achievementMapper;
    }

    public List<AchievementDto> findAllDto() {
        log.info("Fetching all achievements");
        List<AchievementDto> dtos = achievementRepository.findAll()
                .stream()
                .map(achievementMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Found {} achievements", dtos.size());
        return dtos;
    }

    public AchievementDto findByIdDto(Long id) {
        log.info("Fetching achievement by id={}", id);
        return achievementRepository.findById(id)
                .map(achievementMapper::toDto)
                .orElseGet(() -> {
                    log.warn("Achievement with id={} not found", id);
                    return null;
                });
    }

    public AchievementDto saveDto(AchievementDto achievementDto) {
        log.info("Saving achievement: {}", achievementDto);
        Player player = playerRepository.findById(achievementDto.getPlayerId())
                .orElseThrow(() -> {
                    log.error("Player not found with id {}", achievementDto.getPlayerId());
                    return new RuntimeException("Player not found with id " + achievementDto.getPlayerId());
                });

        Achievement saved = achievementRepository.save(
                achievementMapper.toEntity(achievementDto, player)
        );
        log.info("Saved achievement with id={}", saved.getId());
        return achievementMapper.toDto(saved);
    }

    public AchievementDto updateDto(Long id, AchievementDto achievementDto) {
        log.info("Updating achievement with id={}", id);
        Player player = playerRepository.findById(achievementDto.getPlayerId())
                .orElseThrow(() -> {
                    log.error("Player not found with id {}", achievementDto.getPlayerId());
                    return new RuntimeException("Player not found with id " + achievementDto.getPlayerId());
                });

        return achievementRepository.findById(id)
                .map(existing -> {
                    achievementMapper.updateEntityFromDto(achievementDto, existing, player);
                    Achievement updated = achievementRepository.save(existing);
                    log.info("Updated achievement with id={}", updated.getId());
                    return achievementMapper.toDto(updated);
                })
                .orElseGet(() -> {
                    log.warn("Achievement with id={} not found for update", id);
                    return null;
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting achievement with id={}", id);
        achievementRepository.deleteById(id);
        log.info("Deleted achievement with id={}", id);
    }
}
