package com.self.growth.integration.test.service;

import com.self.growth.integration.test.config.SaTokenContext;
import com.self.growth.integration.test.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.self.growth.model.vo.ResResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserService {

    private final UserClient userClient;
    private final SaTokenContext saTokenContext;
    @Value("${test.login.username}")
    private String username;
    @Value("${test.login.password}")
    private String password;

    private boolean isLogin = false;

    public UserService(UserClient userClient, SaTokenContext saTokenContext) {
        this.userClient = userClient;
        this.saTokenContext = saTokenContext;
    }

    public void login() {
        if (!isLogin) {
            ResponseEntity<ResResult<Void>> res = userClient.login(username, password);
            if (Objects.requireNonNull(res.getBody()).getCode() != 200) {
                throw new RuntimeException("用户登录失败");
            }
            isLogin = true;
            log.info("登录成功");
            // 将登录后的token进行保存
            saTokenContext.refreshToken(res.getHeaders());
        }
    }
}
