package org.example.domain.hardware.exception;

public class HardwareNotFoundException extends RuntimeException {
    public HardwareNotFoundException(String msg) {
        super(msg);
    }
}
