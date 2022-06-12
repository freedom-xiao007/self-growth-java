package com.self.growth.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("feedback")
public class FeedbackEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String feedback;
    @TableField("is_process")
    private Boolean isProcess;
    private String email;
}
