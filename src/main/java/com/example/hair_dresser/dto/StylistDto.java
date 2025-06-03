package com.example.hair_dresser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта стилист")
public class StylistDto {

    @Schema(description = "Идентификатор стилиста", example = "1")
    private Long id;

    @NotBlank(message = "Имя стилиста не может быть пустым")
    @Size(max = 100, message = "Имя стилиста не должно превышать 100 символов")
    @Schema(description = "Имя стилиста", example = "Иван Иванов")
    private String name;

    @Size(max = 255, message = "Адрес не должен превышать 255 символов")
    @Schema(description = "Адрес стилиста", example = "123 Main St, Moscow")
    private String address;

    @NotBlank(message = "Контактный email обязателен")
    @Email(message = "Некорректный формат email")
    @Schema(description = "Контактный email стилиста", example = "ivan@example.com")
    private String contactEmail;

    @NotBlank(message = "Дата найма обязательна")
    @Schema(description = "Дата найма стилиста (формат ISO)", example = "2024-05-01T10:00:00")
    private String hireDate;

    @Min(value = 0, message = "Зарплата не может быть отрицательной")
    @Schema(description = "Зарплата стилиста в рублях", example = "50000")
    private Long salary;
}
