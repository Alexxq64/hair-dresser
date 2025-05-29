package com.example.game_library.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта игры")
public class GameDto {

    @Schema(description = "Идентификатор игры", example = "1")
    private Long id;

    @NotBlank(message = "Название игры не может быть пустым")
    @Size(max = 150, message = "Название игры не должно превышать 150 символов")
    @Schema(description = "Название игры", example = "Chess")
    private String title;

    @NotBlank(message = "Жанр игры не может быть пустым")
    @Size(max = 50, message = "Жанр игры не должен превышать 50 символов")
    @Schema(description = "Жанр игры", example = "Strategy")
    private String genre;

    @NotNull(message = "Год выпуска обязателен")
    @Min(value = 1950, message = "Год выпуска должен быть не ранее 1950")
    @Max(value = 2100, message = "Год выпуска должен быть не позднее 2100")
    @Schema(description = "Год выпуска игры", example = "2020")
    private Integer year;

    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    @Schema(description = "Описание игры", example = "Игра о шахматах...")
    private String description;

    @Schema(description = "Категории игры")
    private Set<CategoryDto> categories;
}
