package org.example.exception;

public class DataDuplicationViolationException extends RuntimeException{
    public DataDuplicationViolationException(String message) {
        super(message);
    }
}
