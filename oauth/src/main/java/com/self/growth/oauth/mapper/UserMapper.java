package com.self.growth.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.growth.oauth.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}
