package com.self.growth.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_version")
public class AppVersionEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String version;
    private Integer code;
    private String msg;
    private String url;
}
