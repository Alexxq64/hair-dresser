package com.example.game_library.mapper;

import com.example.game_library.dto.PlayerDto;
import com.example.game_library.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDto toDto(Player player) {
        if (player == null) return null;

        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .rating(player.getRating())
                .build();
    }

    public Player toEntity(PlayerDto dto) {
        if (dto == null) return null;

        Player player = new Player();
        player.setName(dto.getName());
        player.setRating(dto.getRating());
        return player;
    }

    public void updateEntityFromDto(PlayerDto dto, Player player) {
        if (dto == null || player == null) return;

        player.setName(dto.getName());
        player.setRating(dto.getRating());
    }
}
