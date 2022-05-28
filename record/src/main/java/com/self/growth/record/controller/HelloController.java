package com.self.growth.record.controller;

import com.self.growth.record.vo.ResResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    private ResResult<String> hello() {
        return ResResult.success("hello");
    }
}
