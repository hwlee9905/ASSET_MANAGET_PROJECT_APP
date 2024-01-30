package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.login.exception.AuthenticationFailedException;
import org.example.domain.hardware.exception.HardwareNotFoundException;
import org.example.domain.software.exception.SoftwareNotFountException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(DataDuplicationViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult DataDuplicationViolationException(Exception e) {
        log.error("[DataDuplicationViolationException] error", e);
        return new ErrorResult("DataDuplicationViolationException", "이미 등록된 S/N입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HardwareNotFoundException.class, SoftwareNotFountException.class})
    public ErrorResult exHandle(Exception e) {
        log.error("[Exception] error", e);
        return new ErrorResult("NotFoundException", "자산을 찾을 수 없습니다.");
    }
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(AuthenticationFailedException.class)
    public ErrorResult AuthenticationFailedException(Exception e) {
        log.error("[AuthenticationFailedException] error", e);
        return new ErrorResult("AuthenticationFailedException", "해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
    }
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResult HardwareInvalidParameterException(Exception e) {
        log.error("[HardwareInvalidParameterException] error", e);
        return new ErrorResult("HardwareInvalidParameterException", "정렬 속성 또는 정렬 기준이 올바른 값인지 확인해주세요.");
    }
}
