package com.self.growth.integration.test.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.self.growth.integration.test.feign")
public class FeignClientsConfigurationCustom implements RequestInterceptor {

    private final SaTokenContext saTokenContext;

    public FeignClientsConfigurationCustom(SaTokenContext saTokenContext) {
        this.saTokenContext = saTokenContext;
    }

    @Override
    public void apply(RequestTemplate template) {
        final String token = saTokenContext.getToken();
        if (token == null) {
            return;
        }
        template.header(saTokenContext.getKey(), saTokenContext.getToken());
    }
}