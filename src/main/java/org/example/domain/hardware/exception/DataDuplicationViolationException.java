package org.example.domain.hardware.exception;

public class DataDuplicationViolationException extends RuntimeException{
    public DataDuplicationViolationException(String message) {
        super(message);
    }
}
