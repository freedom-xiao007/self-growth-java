package org.self.growth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResResult<T> {

    private T data;
    private int code;
    private String msg;

    public static<T> ResResult<T> success(final T data) {
        return ResResult.<T>builder()
                .code(200)
                .data(data)
                .build();
    }

    public static<T> ResResult<T> success(final T data, final String msg) {
        return ResResult.<T>builder()
                .code(200)
                .data(data)
                .msg(msg)
                .build();
    }

    public static ResResult<Void> err(final int code, final String msg) {
        return ResResult.<Void>builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
