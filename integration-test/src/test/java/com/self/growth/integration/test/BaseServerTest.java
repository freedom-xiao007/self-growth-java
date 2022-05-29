package com.self.growth.integration.test;

import com.self.growth.integration.test.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServerTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void login() {
        userService.login();
    }
}
