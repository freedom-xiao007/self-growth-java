package com.self.growth.record.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.self.growth.record.vo.ResResult;
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
    public ResResult<?> handlerException(Exception e) {
        e.printStackTrace();
        return ResResult.err(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResResult<?>> handlerException(BusinessException e) {
        return ResponseEntity.status(e.getCode()).body(ResResult.err(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<ResResult<?>> handlerException(NotLoginException ignoredE) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResResult.err(HttpStatus.UNAUTHORIZED.value(), "用户未登录"));
    }
}

