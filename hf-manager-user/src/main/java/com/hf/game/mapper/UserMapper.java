package com.hf.game.mapper;

import com.hf.game.module.pojo.userManager.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by 123 on 2019-6-3.
 */
@Mapper
public interface UserMapper {
    User selectById(Long id);
}
