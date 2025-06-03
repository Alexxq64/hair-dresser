package com.example.hair_dresser.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта клиента")
public class ClientDto {

    @Schema(description = "Идентификатор клиента", example = "1")
    private Long id;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @Size(max = 100, message = "Имя клиента не должно превышать 100 символов")
    @Schema(description = "Имя клиента", example = "John Doe")
    private String name;

    @Min(value = 0, message = "Рейтинг не может быть отрицательным")
    @Schema(description = "Рейтинг клиента", example = "1200")
    private int rating;

    @Email(message = "Email должен быть валидным")
    @Schema(description = "Email клиента", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Дата рождения клиента", example = "1990-01-01")
    private String birthDate;

    @Schema(description = "Дата регистрации клиента", example = "2023-05-28")
    private String registeredAt;

    @Schema(description = "Список визитов клиента")
    private List<VisitDto> visits;
}
