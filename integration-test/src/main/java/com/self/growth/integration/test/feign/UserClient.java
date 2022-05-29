package com.self.growth.integration.test.feign;

import org.self.growth.model.vo.ResResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "UserClient",
        url = "${test.server.auth.url}"
)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/sso/doLogin")
    ResponseEntity<ResResult<Void>> login(@RequestParam("name") String name, @RequestParam("pwd") String pwd);
}
