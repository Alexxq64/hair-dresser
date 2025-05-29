package com.example.game_library.mapper;

import com.example.game_library.dto.GameDto;
import com.example.game_library.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameDto toDto(Game game) {
        if (game == null) return null;

        return GameDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .genre(game.getGenre())
                .year(game.getYear())
                .build();
    }

    public Game toEntity(GameDto dto) {
        if (dto == null) return null;

        Game game = new Game();
        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setYear(dto.getYear());
        return game;
    }

    public void updateEntityFromDto(GameDto dto, Game game) {
        if (dto == null || game == null) return;

        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setYear(dto.getYear());
    }
}
