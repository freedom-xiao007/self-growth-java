package com.self.growth.task.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo<T> {

    private T data;
    private int code;
    private String msg;
    private long total;

    public static ResponseVo<?> success(Object data, String message) {
        return ResponseVo.builder()
                .data(data)
                .msg(message)
                .code(200)
                .build();
    }

    public static ResponseVo<?> success(String message) {
        return ResponseVo.builder()
                .msg(message)
                .code(200)
                .build();
    }

    public static ResponseVo success() {
        return ResponseVo.builder()
                .code(200)
                .build();
    }
}
