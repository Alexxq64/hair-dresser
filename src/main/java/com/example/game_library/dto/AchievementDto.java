package com.example.game_library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта достижения")
public class AchievementDto {

    @Schema(description = "Идентификатор достижения", example = "1")
    private Long id;

    @NotNull(message = "ID игрока обязателен")
    @Schema(description = "ID игрока", example = "10")
    private Long playerId;

    @NotBlank(message = "Название достижения не может быть пустым")
    @Size(max = 100, message = "Название достижения не должно превышать 100 символов")
    @Schema(description = "Название достижения", example = "Победа в турнире")
    private String title;

    @Size(max = 255, message = "Описание достижения не должно превышать 255 символов")
    @Schema(description = "Описание достижения", example = "Выиграл финальный матч чемпионата")
    private String description;

    @Schema(description = "Дата достижения")
    private LocalDateTime achievedAt;
}
