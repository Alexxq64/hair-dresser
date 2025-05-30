package com.example.game_library.baseresponse;

import org.springframework.stereotype.Service;

@Service
public class BaseResponseService {

    public <T> ResponseWrapper<T> createSuccessResponse(T data) {
        return ResponseWrapper.success(data);
    }

    public <T> ResponseWrapper<T> createErrorResponse(String code, String message) {
        return ResponseWrapper.error(code, message);
    }

    public ResponseWrapper<?> createErrorResponse(String code, String message, Object details) {
        return new ResponseWrapper<>(null, new ErrorDto(code, message, details));
    }
}
