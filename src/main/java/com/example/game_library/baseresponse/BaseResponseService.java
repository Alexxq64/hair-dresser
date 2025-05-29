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
}
