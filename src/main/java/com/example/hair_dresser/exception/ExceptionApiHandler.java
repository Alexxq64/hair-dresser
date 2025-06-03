package com.example.hair_dresser.exception;

import com.example.hair_dresser.baseresponse.BaseResponseService;
import com.example.hair_dresser.baseresponse.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionApiHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionApiHandler.class);
    private final BaseResponseService responseService;

    public ExceptionApiHandler(BaseResponseService responseService) {
        this.responseService = responseService;
    }

    // Обработка бизнес-исключений
    @ExceptionHandler(PenzGtuException.class)
    public ResponseWrapper<?> handlePenzGtuException(PenzGtuException ex) {
        log.warn("⚠️ Бизнес-ошибка: {}", ex.getMessage());
        return responseService.createErrorResponse(ex.getErrorType().name(), ex.getMessage());
    }

    // Обработка ошибок валидации DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("❌ Ошибка валидации: {}", errors);

        return responseService.createErrorResponse("VALIDATION_ERROR", "Ошибка валидации", errors);
    }

    // Обработка всех прочих исключений
    @ExceptionHandler(Exception.class)
    public ResponseWrapper<?> handleOtherExceptions(Exception ex) {
        log.error("💥 Необработанная ошибка: {}", ex.getMessage(), ex);
        return responseService.createErrorResponse("SYSTEM_ERROR", "Внутренняя ошибка сервера");
    }
}
