package com.self.growth.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.growth.auth.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}
