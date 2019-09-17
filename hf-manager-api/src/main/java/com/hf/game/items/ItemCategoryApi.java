package com.hf.game.items;

import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.CateGoryResult;
import com.hf.game.module.items.ext.ItemCategoryNode;
import io.swagger.annotations.Api;


/**
 * Created by 123 on 2019-6-20.
 */
@Api(value ="商品分类管理",description = "提供商品分类的增删改查功能")
public interface ItemCategoryApi {
    ResponseResult<CateGoryResult> findAll();

    ResponseResult<ItemCategoryNode> findAllByServer();
}
