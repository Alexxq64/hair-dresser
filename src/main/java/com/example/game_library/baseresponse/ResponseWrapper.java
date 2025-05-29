package com.example.game_library.baseresponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Обёртка ответа API")
public class ResponseWrapper<T> {

    @Schema(description = "Данные ответа")
    private T data;

    @Schema(description = "Информация об ошибке")
    private ErrorDto error;

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(data, null);
    }

    public static <T> ResponseWrapper<T> error(String code, String message) {
        return new ResponseWrapper<>(null, new ErrorDto(code, message));
    }
}
