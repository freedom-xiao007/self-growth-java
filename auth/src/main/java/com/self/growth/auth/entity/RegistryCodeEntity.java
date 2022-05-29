package com.self.growth.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册邀请码
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistryCodeEntity {

    private Long id;
    private String code;
    private Boolean used;
}
