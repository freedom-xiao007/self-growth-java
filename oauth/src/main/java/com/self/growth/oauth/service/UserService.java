package com.self.growth.oauth.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.self.growth.oauth.config.Constants;
import com.self.growth.oauth.config.MySaTokenContext;
import com.self.growth.oauth.entity.RegistryCodeEntity;
import com.self.growth.oauth.entity.UserEntity;
import com.self.growth.oauth.exception.BusinessException;
import com.self.growth.oauth.mapper.RegistryCodeMapper;
import com.self.growth.oauth.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final RegistryCodeMapper registryCodeMapper;
    private final MySaTokenContext mySaTokenContext;

    public UserService(UserMapper userMapper, RegistryCodeMapper registryCodeMapper, MySaTokenContext mySaTokenContext) {
        this.userMapper = userMapper;
        this.registryCodeMapper = registryCodeMapper;
        this.mySaTokenContext = mySaTokenContext;
    }

    public void login(final String username, final String password) {
        final String deviceType = mySaTokenContext.getHeardValue(Constants.LOGIN_DEVICE_TYPE);
        final UserEntity user = userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername, username)
                .eq(UserEntity::getIsDelete, false)
        );
        if (user == null) {
            final String registryCode = mySaTokenContext.getHeardValue(Constants.REGISTRY_CODE);
            if (registryCode != null && !registryCode.isEmpty()) {
                long userId = registryUser(username, password, registryCode);
                mySaTokenContext.login(userId, deviceType);
                return;
            }
            throw BusinessException.badRequest("用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            throw BusinessException.badRequest("用户密码错误");
        }
        mySaTokenContext.login(user.getId(), deviceType);
    }

    private long registryUser(final String username, final String password, final String code) {
        boolean isValid = registryCodeMapper.selectCount(Wrappers.<RegistryCodeEntity>lambdaQuery()
                .eq(RegistryCodeEntity::getUsed, false)
                .eq(RegistryCodeEntity::getCode, code)
        ) > 0;
        if (!isValid) {
            throw BusinessException.badRequest("注册邀请码已失效");
        }

        final UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(password)
                .createTime(new Date())
                .isDelete(false)
                .updateTime(new Date())
                .build();
        userMapper.insert(newUser);
        return newUser.getId();
    }
}
