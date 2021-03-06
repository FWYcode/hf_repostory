package com.hf.game.search;

import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.module.items.ItemPub;
import com.hf.game.module.search.SearchQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by 123 on 2019-7-6.
 */
@Api(value = "商品搜索服务",description = "提供商品搜索服务")
public interface ItemSearchApi {
    @ApiOperation("搜索")
    QueryResponseResult<ItemPub> searchList(int page, int size, SearchQueryParam param);
}
