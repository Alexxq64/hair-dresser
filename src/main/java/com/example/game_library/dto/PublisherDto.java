package com.example.game_library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта издателя")
public class PublisherDto {

    @Schema(description = "Идентификатор издателя", example = "1")
    private Long id;

    @NotBlank(message = "Название издателя не может быть пустым")
    @Size(max = 100, message = "Название издателя не должно превышать 100 символов")
    @Schema(description = "Название издателя", example = "Epic Games")
    private String name;

    @Size(max = 255, message = "Адрес не должен превышать 255 символов")
    @Schema(description = "Адрес издателя", example = "123 Main St, New York, NY")
    private String address;

    @NotBlank(message = "Контактный email обязателен")
    @Email(message = "Некорректный формат email")
    @Schema(description = "Контактный email издателя", example = "contact@epicgames.com")
    private String contactEmail;
}
