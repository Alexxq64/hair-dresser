package com.example.hair_dresser.baseresponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация об ошибке")
public class ErrorDto {

    @Schema(description = "Код ошибки")
    private String code;

    @Schema(description = "Сообщение ошибки")
    private String message;

    private Object details;

    // Конструктор без details
    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
        this.details = null;
    }
}
