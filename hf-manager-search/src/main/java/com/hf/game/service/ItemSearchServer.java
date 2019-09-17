package com.hf.game.service;

import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.module.items.ItemPub;
import com.hf.game.module.search.SearchQueryParam;

/**
 * Created by 123 on 2019-7-6.
 */
public interface ItemSearchServer {
    QueryResponseResult<ItemPub> seachList(int page, int size, SearchQueryParam param);
}
