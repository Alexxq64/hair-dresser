package com.example.hair_dresser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта отзыва")
public class FeedbackDto {

    @Schema(description = "Идентификатор отзыва", example = "1")
    private Long id;

    @NotNull(message = "ID процедуры обязателен")
    @Schema(description = "ID процедуры", example = "10")
    private Long procedureId;

    @NotNull(message = "ID клиента обязателен")
    @Schema(description = "ID клиента", example = "5")
    private Long clientId;

    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 10, message = "Рейтинг должен быть не больше 10")
    @Schema(description = "Оценка отзыва", example = "8")
    private int rating;

    @Size(max = 500, message = "Комментарий не должен превышать 500 символов")
    @Schema(description = "Комментарий к отзыву", example = "Отличная процедура!")
    private String comment;

    @Schema(description = "Дата создания отзыва", example = "2025-06-03T14:30:00")
    private String createdAt;

    @Schema(description = "Дата последнего обновления отзыва", example = "2025-06-03T16:00:00")
    private String updatedAt;
}
