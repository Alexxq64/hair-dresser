package com.example.hair_dresser.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта процедуры")
public class ProcedureDto {

    @Schema(description = "Идентификатор процедуры", example = "1")
    private Long id;

    @NotBlank(message = "Название процедуры не может быть пустым")
    @Size(max = 150, message = "Название процедуры не должно превышать 150 символов")
    @Schema(description = "Название процедуры", example = "Мужская стрижка")
    private String name;

    @NotBlank(message = "Тип процедуры не может быть пустым")
    @Size(max = 50, message = "Тип процедуры не должен превышать 50 символов")
    @Schema(description = "Тип процедуры (например, MALE, FEMALE, UNISEX)", example = "MALE")
    private String procedureType;

    @NotNull(message = "Длительность обязательна")
    @Min(value = 1, message = "Длительность должна быть минимум 1 минута")
    @Max(value = 1440, message = "Длительность не должна превышать 1440 минут")
    @Schema(description = "Длительность процедуры в минутах", example = "45")
    private Integer durationMinutes;

    @NotNull(message = "Цена обязательна")
    @Min(value = 1, message = "Цена должна быть положительной")
    @Schema(description = "Цена процедуры в рублях", example = "1500")
    private Long price;

    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    @Schema(description = "Описание процедуры", example = "Классическая мужская стрижка машинкой и ножницами")
    private String description;

    @Schema(description = "Категории процедуры")
    private Set<CategoryDto> categories;
}
