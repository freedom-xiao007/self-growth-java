package com.self.growth.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
@Builder
@TableName("user_info")
public class UserEntity {

    private Long id;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Boolean isDelete;
}
