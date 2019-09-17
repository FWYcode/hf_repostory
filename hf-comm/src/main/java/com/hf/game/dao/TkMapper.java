package com.hf.game.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by 123 on 2019-6-2.
 */
public interface TkMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
