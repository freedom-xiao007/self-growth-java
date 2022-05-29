package com.self.growth.record.controller;

import com.self.growth.record.config.MySaTokenContext;
import org.self.growth.model.vo.ResResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final MySaTokenContext mySaTokenContext;

    public HelloController(MySaTokenContext mySaTokenContext) {
        this.mySaTokenContext = mySaTokenContext;
    }

    @GetMapping("/hello")
    private ResResult<String> hello() {
        return ResResult.success(String.format("hello: %d", mySaTokenContext.loginUserId()));
    }
}
