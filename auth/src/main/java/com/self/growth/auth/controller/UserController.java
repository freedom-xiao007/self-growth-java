package com.self.growth.auth.controller;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoHandle;
import com.self.growth.auth.exception.BusinessException;
import com.self.growth.auth.service.UserService;
import org.self.growth.model.vo.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sa-Token-SSO Server端 Controller
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * SSO-Server端：处理所有SSO相关请求
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoHandle.serverRequest();
    }

    /**
     * 配置SSO相关参数
     */
    @Autowired
    private void configSso(SaSsoConfig sso) {
        // 配置：未登录时返回的View
        sso.setNotLoginView(() -> {
            throw BusinessException.unauthorized("用户未登录");
        });

        // 配置：登录处理函数
        sso.setDoLoginHandle((name, pwd) -> {
            userService.login(name, pwd);
            return ResResult.success(null, "登录成功");
        });
    }
}
