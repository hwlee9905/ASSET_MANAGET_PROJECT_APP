package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.login.exception.AuthenticationFailedException;
import org.example.domain.hardware.exception.DataDuplicationViolationException;
import org.example.domain.hardware.exception.HardwareInvalidParameterException;
import org.example.domain.hardware.exception.HardwareNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult exHandle(DataDuplicationViolationException e) {
        log.error("[DataDuplicationViolationException] error", e);
        return new ErrorResult("DataDuplicationViolationException", "이미 등록된 S/N입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(HardwareNotFoundException e) {
        log.error("[NotFoundHardwareException] error", e);
        return new ErrorResult("NotFoundHardwareException", "해당 ID에 해당하는 자산이 없습니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(PropertyReferenceException e) {
        log.error("[PropertyReferenceException] error", e);
        return new ErrorResult("PropertyReferenceException", "해당하는 속성이 없습니다.");
    }

    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler
    public ErrorResult exHandle(AuthenticationFailedException e) {
        log.error("[AuthenticationFailedException] error", e);
        return new ErrorResult("AuthenticationFailedException", "해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
    }
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler
    public ErrorResult exHandle(HardwareInvalidParameterException e) {
        log.error("[HardwareInvalidParameterException] error", e);
        return new ErrorResult("HardwareInvalidParameterException", "정렬 속성 또는 정렬 기준이 올바른 값인지 확인해주세요.");
    }
}
