package com.hf.game.items;

import com.hf.game.module.items.ItemBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 123 on 2019-6-19.
 */
@Api(value = "商品品牌管理接口", description = "提供商品品牌的增删改查功能")
public interface ItemBrandActionApi {
    @ApiOperation("商品id查询")
    ItemBrand findById(HttpServletRequest res,Integer id);

    @ApiOperation("查询所有商品")
    List<ItemBrand> findAll();

    @ApiOperation("添加商品")
    ItemBrand insert(ItemBrand itemBrand);

}
