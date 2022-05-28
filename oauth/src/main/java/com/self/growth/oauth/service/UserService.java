package com.self.growth.oauth.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.self.growth.oauth.entity.UserEntity;
import com.self.growth.oauth.exception.BusinessException;
import com.self.growth.oauth.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    public void login(final String username, final String password) {
//        final UserEntity user = userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery()
//                .eq(UserEntity::getUsername, username)
//                .eq(UserEntity::getIsDelete, false)
//        );
//        if (user == null) {
//            throw BusinessException.badRequest("用户不存在");
//        }
//
//        if (!user.getPassword().equals(password)) {
//            throw BusinessException.badRequest("用户密码错误");
//        }
//        StpUtil.login(user.getId());
//    }

    public void login(final String username, final String password) {
        final UserEntity user = UserEntity.builder()
                .id(1L)
                .username("testUser")
                .password("password")
                .build();
        if (!user.getUsername().equals(username)) {
            throw BusinessException.badRequest("用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            throw BusinessException.badRequest("用户密码错误");
        }
        StpUtil.login(user.getId());
    }
}
