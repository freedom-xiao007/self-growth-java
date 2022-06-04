package com.self.growth.auth.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.self.growth.auth.config.Constants;
import com.self.growth.auth.config.MySaTokenContext;
import com.self.growth.auth.entity.RegistryCodeEntity;
import com.self.growth.auth.entity.UserEntity;
import com.self.growth.auth.exception.BusinessException;
import com.self.growth.auth.mapper.RegistryCodeMapper;
import com.self.growth.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final RegistryCodeMapper registryCodeMapper;
    private final MySaTokenContext mySaTokenContext;
    @Value("${application.auth.need-register-code}")
    private boolean needRegisterCode;

    public UserService(UserMapper userMapper,
                       RegistryCodeMapper registryCodeMapper,
                       MySaTokenContext mySaTokenContext) {
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
            if (!needRegisterCode || (registryCode != null && !registryCode.isEmpty())) {
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
        if (!checkEmailFormat(username)) {
            throw BusinessException.badRequest("邮箱格式错误");
        }

        if (needRegisterCode) {
            boolean isValid = registryCodeMapper.selectCount(Wrappers.<RegistryCodeEntity>lambdaQuery()
                    .eq(RegistryCodeEntity::getUsed, false)
                    .eq(RegistryCodeEntity::getCode, code)
            ) > 0;
            if (!isValid) {
                throw BusinessException.badRequest("注册邀请码已失效");
            }
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

    /**
     *检查Email 格式（正则表达式）
     * @param email email
     * @return 游戏格式是否正确
     */
    private boolean checkEmailFormat(String email){
        /*
         * " \w"：匹配字母、数字、下划线。等价于'[A-Za-z0-9_]'。
         * "|"  : 或的意思，就是二选一
         * "*" : 出现0次或者多次
         * "+" : 出现1次或者多次
         * "{n,m}" : 至少出现n个，最多出现m个
         * "$" : 以前面的字符结束
         */
        String REGEX="^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
        Pattern p = Pattern.compile(REGEX);
        Matcher matcher=p.matcher(email);
        return matcher.matches();
    }
}
