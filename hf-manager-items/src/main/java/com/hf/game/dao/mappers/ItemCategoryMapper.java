package com.hf.game.dao.mappers;

import com.hf.game.module.items.ext.ItemCategoryNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 123 on 2019-6-20.
 */
@Mapper
public interface ItemCategoryMapper {
    List<ItemCategoryNode> findAll();
}
