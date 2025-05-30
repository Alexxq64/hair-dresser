package com.example.game_library.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта игрока")
public class PlayerDto {

    @Schema(description = "Идентификатор игрока", example = "1")
    private Long id;

    @NotBlank(message = "Имя игрока не может быть пустым")
    @Size(max = 100, message = "Имя игрока не должно превышать 100 символов")
    @Schema(description = "Имя игрока", example = "John Doe")
    private String name;

    @Min(value = 0, message = "Рейтинг не может быть отрицательным")
    @Schema(description = "Рейтинг игрока", example = "1200")
    private int rating;

    @Email(message = "Email должен быть валидным")
    @Schema(description = "Email игрока", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Дата рождения игрока", example = "1990-01-01")
    private String birthDate;

    @Schema(description = "Дата регистрации игрока", example = "2023-05-28")
    private String registeredAt;

    @Schema(description = "Список достижений игрока")
    private List<AchievementDto> achievements;
}
