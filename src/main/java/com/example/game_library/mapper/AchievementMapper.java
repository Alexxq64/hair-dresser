package com.example.game_library.mapper;

import com.example.game_library.dto.AchievementDto;
import com.example.game_library.entity.Achievement;
import com.example.game_library.entity.Player;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AchievementMapper {

    public AchievementDto toDto(Achievement achievement) {
        if (achievement == null) return null;

        return AchievementDto.builder()
                .id(achievement.getId())
                .playerId(achievement.getPlayer().getId())  // берём id из Player
                .title(achievement.getTitle())
                .description(achievement.getDescription())
                .build();
    }

    // Теперь нужен Player объект, а не просто Long
    public Achievement toEntity(AchievementDto dto, Player player) {
        if (dto == null || player == null) return null;

        return Achievement.builder()
                .player(player)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .achievedAt(LocalDateTime.now())
                .build();
    }

    public void updateEntityFromDto(AchievementDto dto, Achievement achievement, Player player) {
        if (dto == null || achievement == null || player == null) return;

        achievement.setPlayer(player);
        achievement.setTitle(dto.getTitle());
        achievement.setDescription(dto.getDescription());
        achievement.setAchievedAt(LocalDateTime.now());
    }
}
