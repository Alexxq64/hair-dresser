package com.example.game_library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO объекта игры")
public class GameDto {

    @Schema(description = "Идентификатор игры", example = "1")
    private Long id;

    @NotBlank(message = "Название игры не может быть пустым")
    @Size(max = 100, message = "Название игры не должно превышать 100 символов")
    @Schema(description = "Название игры", example = "Chess")
    private String title;

    @NotBlank(message = "Жанр игры не может быть пустым")
    @Schema(description = "Жанр игры", example = "Strategy")
    private String genre;

    @Min(value = 1950, message = "Год выпуска должен быть не ранее 1950")
    @Schema(description = "Год выпуска игры", example = "2020")
    private int year;
}
