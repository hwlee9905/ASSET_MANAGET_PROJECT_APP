package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.MissingFormatArgumentException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResult> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("[DataIntegrityViolationException] error", e);
        ErrorResult errorResult = new ErrorResult("DataIntegrityViolationException", "이미 등록된 S/N입니다.");
        return new ResponseEntity<>(errorResult, HttpStatus.CONFLICT);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(DataRetrievalFailureException e) {
        log.error("[DataRetrievalFailureException] error", e);
        return new ErrorResult("DataRetrievalFailureException", "해당 ID에 해당하는 자산이 없습니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(PropertyReferenceException e) {
        log.error("[PropertyReferenceException] error", e);
        return new ErrorResult("PropertyReferenceException", "해당하는 속성이 없습니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(MissingFormatArgumentException e) {
        log.error("[MissingFormatArgumentException] error", e);
        return new ErrorResult("MissingFormatArgumentException", "정렬 속성또는 정렬 기준이 필요합니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(IllegalArgumentException e) {
        log.error("[IllegalArgumentException] error", e);
        return new ErrorResult("IllegalArgumentException", "정렬 기준은 DESC 또는 ASC이어야 합니다.");
    }
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler
    public ErrorResult exHandle(IllegalStateException e) {
        log.error("[IllegalStateException] error", e);
        return new ErrorResult("IllegalStateException", "해당 작업을 수행하기 위한 권한이 없습니다. 관리자 계정으로 로그인해주세요.");
    }

}
