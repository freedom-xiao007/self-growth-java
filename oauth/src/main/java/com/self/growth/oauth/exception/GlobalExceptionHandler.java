package com.self.growth.oauth.exception;

import com.self.growth.oauth.vo.ResResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常拦截
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResResult<?> handlerException(final Exception e) {
        e.printStackTrace();
        return ResResult.err(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResResult<?>> handlerException(final BusinessException e) {
        return ResponseEntity.status(e.getCode()).body(ResResult.err(e.getCode(), e.getMessage()));
    }
}

