package com.hf.game.action;

import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.module.items.ItemPub;
import com.hf.game.module.search.SearchQueryParam;
import com.hf.game.search.ItemSearchApi;
import com.hf.game.service.ItemSearchServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2019-7-6.
 */
@RestController
@RequestMapping("items/search")
public class ItemSearchAction implements ItemSearchApi {
    @Autowired
    ItemSearchServer itemSearchServer;
    @Override
    @GetMapping("list/{page}/{size}")
    public QueryResponseResult<ItemPub> searchList(@PathVariable( "page") int page, @PathVariable("size") int size, SearchQueryParam param) {
//        System.out.println(page);
//        System.out.println(param);
        return itemSearchServer.seachList(page, size, param);
    }
}
