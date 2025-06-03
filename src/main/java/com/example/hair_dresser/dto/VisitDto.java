package com.example.hair_dresser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO объекта визит")
public class VisitDto {

    @Schema(description = "Идентификатор визита", example = "1")
    private Long id;

    @NotNull(message = "ID клиента обязателен")
    @Schema(description = "ID клиента", example = "10")
    private Long clientId;

    @NotNull(message = "ID процедуры обязателен")
    @Schema(description = "ID процедуры", example = "5")
    private Long procedureId;

    @Size(max = 255, message = "Заметки не должны превышать 255 символов")
    @Schema(description = "Заметки стилиста", example = "Попросил укоротить виски, не трогать макушку")
    private String notes;

    @NotNull(message = "Дата и время визита обязательны")
    @Schema(description = "Дата и время визита", example = "2025-06-03T15:30:00")
    private LocalDateTime visitDateTime;
}
