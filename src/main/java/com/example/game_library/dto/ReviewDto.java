package com.example.game_library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта отзыва")
public class ReviewDto {

    @Schema(description = "Идентификатор отзыва", example = "1")
    private Long id;

    @NotNull(message = "ID игры обязателен")
    @Schema(description = "ID игры", example = "10")
    private Long gameId;

    @NotNull(message = "ID игрока обязателен")
    @Schema(description = "ID игрока", example = "5")
    private Long playerId;

    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 10, message = "Рейтинг должен быть не больше 10")
    @Schema(description = "Оценка отзыва", example = "8")
    private int rating;

    @Size(max = 500, message = "Комментарий не должен превышать 500 символов")
    @Schema(description = "Комментарий к отзыву", example = "Отличная игра!")
    private String comment;
}
