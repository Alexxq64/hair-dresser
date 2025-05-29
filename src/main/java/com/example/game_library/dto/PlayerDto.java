package com.example.game_library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
