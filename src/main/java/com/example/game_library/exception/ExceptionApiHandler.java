package com.example.game_library.exception;

import com.example.game_library.baseresponse.BaseResponseService;
import com.example.game_library.baseresponse.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    private final BaseResponseService responseService;

    public ExceptionApiHandler(BaseResponseService responseService) {
        this.responseService = responseService;
    }

    @ExceptionHandler(PenzGtuException.class)
    public ResponseWrapper<?> handlePenzGtuException(PenzGtuException ex) {
        // всегда возвращаем 200, но с обёрткой, где есть ошибка
        return responseService.createErrorResponse(ex.getErrorType().name(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<?> handleOtherExceptions(Exception ex) {
        // Логируем ошибку
        ex.printStackTrace();
        return responseService.createErrorResponse("SYSTEM_ERROR", "Внутренняя ошибка сервера");
    }
}
