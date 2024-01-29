package org.example.domain.hardware.exception;

public class HardwareInvalidParameterException extends RuntimeException{
    public HardwareInvalidParameterException(String message) {
        super(message);
    }
}
