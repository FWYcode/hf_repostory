package com.hf.game.mapper;

import com.hf.game.module.pojo.userManager.Power;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 123 on 2019-6-3.
 */
@Mapper
public interface PowerMapper {
    Power selectById(Long id);
}
