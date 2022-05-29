package com.self.growth.auth.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;

@Component
public class MySaTokenContext {

    public String getHeardValue(final String name) {
        return SaHolder.getRequest().getParam(name);
    }

    public void login(final long id, final String deviceType) {
        StpUtil.login(id, deviceType);
    }
}
