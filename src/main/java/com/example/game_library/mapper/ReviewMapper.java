package com.example.game_library.mapper;

import com.example.game_library.dto.ReviewDto;
import com.example.game_library.entity.Game;
import com.example.game_library.entity.Review;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review) {
        if (review == null) return null;

        return ReviewDto.builder()
                .id(review.getId())
                // Получаем gameId из связанного объекта Game
                .gameId(review.getGame() != null ? review.getGame().getId() : null)
                .playerId(review.getPlayerId())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

    public Review toEntity(ReviewDto dto) {
        if (dto == null) return null;

        return Review.builder()
                // Создаём объект Game с id из dto
                .game(dto.getGameId() != null ? Game.builder().id(dto.getGameId()).build() : null)
                .playerId(dto.getPlayerId())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateEntityFromDto(ReviewDto dto, Review review) {
        if (dto == null || review == null) return;

        // Обновляем связанный объект Game
        if (dto.getGameId() != null) {
            review.setGame(Game.builder().id(dto.getGameId()).build());
        } else {
            review.setGame(null);
        }

        review.setPlayerId(dto.getPlayerId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
    }
}
