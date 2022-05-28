package com.self.growth.oauth.service;

import com.self.growth.oauth.config.MySaTokenContext;
import com.self.growth.oauth.entity.UserEntity;
import com.self.growth.oauth.exception.BusinessException;
import com.self.growth.oauth.mapper.RegistryCodeMapper;
import com.self.growth.oauth.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

    private final UserMapper userMapper = Mockito.mock(UserMapper.class);
    private final RegistryCodeMapper registryCodeMapper = Mockito.mock(RegistryCodeMapper.class);
    private final MySaTokenContext mySaTokenContext = Mockito.mock(MySaTokenContext.class);
    private final UserService userService = new UserService(userMapper, registryCodeMapper, mySaTokenContext);

    @Test
    public void loginWithErrorUser() {
        Mockito.when(mySaTokenContext.getHeardValue(Mockito.any())).thenReturn("");
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        BusinessException e = Assertions.assertThrows(BusinessException.class, () -> userService.login("errorUser", "123456"));
        Assertions.assertEquals("用户不存在", e.getMessage());
    }

    @Test
    public void loginWithErrorPassword() {
        Mockito.when(mySaTokenContext.getHeardValue(Mockito.any())).thenReturn("");
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(UserEntity.builder().username("user").password("password").build());
        BusinessException e = Assertions.assertThrows(BusinessException.class, () -> userService.login("user", "123456"));
        Assertions.assertEquals("用户密码错误", e.getMessage());
    }

    @Test
    public void loginSuccess() {
        Mockito.when(mySaTokenContext.getHeardValue(Mockito.anyString())).thenReturn("");
        Mockito.doNothing().when(mySaTokenContext).login(Mockito.anyInt(), Mockito.anyString());
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(UserEntity.builder().id(1L).username("user").password("123456").build());
        Assertions.assertDoesNotThrow(() -> userService.login("user", "123456"));
    }

    @Test
    public void registryWithErrorCode() {
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        Mockito.when(mySaTokenContext.getHeardValue(Mockito.any())).thenReturn("-");
        Mockito.when(registryCodeMapper.selectCount(Mockito.any())).thenReturn(0L);
        BusinessException e = Assertions.assertThrows(BusinessException.class, () -> userService.login("user", "123456"));
        Assertions.assertEquals("注册邀请码已失效", e.getMessage());
    }

    @Test
    public void registrySuccess() {
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        Mockito.when(mySaTokenContext.getHeardValue(Mockito.any())).thenReturn("-");
        Mockito.when(registryCodeMapper.selectCount(Mockito.any())).thenReturn(1L);
        Mockito.doAnswer(invocationOnMock -> {
            UserEntity user = invocationOnMock.getArgument(0);
            user.setId(1L);
            return 1;
        }).when(userMapper).insert(Mockito.any(UserEntity.class));
        Assertions.assertDoesNotThrow(() -> userService.login("user", "123456"));
    }
}
