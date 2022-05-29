package com.self.growth.integration.test.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaTokenContext {

    private String token;
    private String key;

    /**
     * 从登录返回结果中获取Token信息
     *
     * 基于SaToken登录认证框架，针对其返回特定进行处理提前
     * @param headers 登录返回的headers
     */
    public void refreshToken(final HttpHeaders headers) {
        final List<String> setCookie = headers.get("set-cookie");
        assert setCookie != null;
        if (setCookie.isEmpty()) {
            return;
        }
        final String originCookie = setCookie.get(0);
        key = originCookie.split(";")[0].split("=")[0];
        token = originCookie.split(";")[0].split("=")[1];
    }

    public String getToken() {
        return token;
    }

    public String getKey() {
        return key;
    }
}
