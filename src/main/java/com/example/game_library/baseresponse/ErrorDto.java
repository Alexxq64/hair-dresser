package com.example.game_library.baseresponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Информация об ошибке")
public class ErrorDto {
    @Schema(description = "Код ошибки")
    private String code;

    @Schema(description = "Сообщение ошибки")
    private String message;
}
