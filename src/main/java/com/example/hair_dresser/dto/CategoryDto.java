package com.example.hair_dresser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта категории")
public class CategoryDto {

    @Schema(description = "Идентификатор категории", example = "1")
    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    @Size(max = 50, message = "Название категории не должно превышать 50 символов")
    @Schema(description = "Название категории", example = "RPG")
    private String name;

    @Size(max = 255, message = "Описание категории не должно превышать 255 символов")
    @Schema(description = "Описание категории", example = "Ролевые процедуры")
    private String description;
}
