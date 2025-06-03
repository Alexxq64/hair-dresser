package com.example.hair_dresser.exception;

public class PenzGtuException extends RuntimeException {
    private final ErrorType errorType;

    public PenzGtuException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
