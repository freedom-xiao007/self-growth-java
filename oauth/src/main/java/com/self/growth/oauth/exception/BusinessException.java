package com.self.growth.oauth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(final String msg, final int code) {
        super(msg);
        this.code = code;
    }

    public static BusinessException badRequest(final String msg) {
        return new BusinessException(msg, HttpStatus.BAD_REQUEST.value());
    }

    public static BusinessException unauthorized(final String msg) {
        return new BusinessException(msg, HttpStatus.UNAUTHORIZED.value());
    }
}
