package com.hf.game.mapper;

import com.hf.game.module.pojo.userManager.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 123 on 2019-6-3.
 */
@Mapper
public interface RoleMapper {
    Role selectById(Long id);
}
