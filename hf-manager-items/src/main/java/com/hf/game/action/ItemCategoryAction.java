package com.hf.game.action;

import com.hf.game.items.ItemCategoryApi;
import com.hf.game.modul.response.CommonCode;
import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.modul.response.QueryResult;
import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.CateGoryResult;
import com.hf.game.module.items.ext.ItemCategoryNode;
import com.hf.game.service.ItemCategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 123 on 2019-6-20.
 */
@RestController
@RequestMapping("item/category")
public class ItemCategoryAction implements ItemCategoryApi {
    @Autowired
    ItemCategoryServer categoryServer;

    @GetMapping("all")
    @Override
    public ResponseResult<CateGoryResult> findAll() {
        List<CateGoryResult> all = categoryServer.findAll();
        QueryResult<CateGoryResult> queryResult = new QueryResult<>();
        queryResult.setList(all);
        queryResult.setTotal(Integer.toUnsignedLong(all.size()));
        ResponseResult<CateGoryResult> result = new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
//        System.out.println(all.toString().length());
        return result;
    }

    @Override
    @GetMapping("allbyserver")
    public ResponseResult<ItemCategoryNode> findAllByServer() {
        List<ItemCategoryNode> allByServer = categoryServer.findAllByServer();
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(Integer.toUnsignedLong(allByServer.size()));
        queryResult.setList(allByServer);
        ResponseResult<ItemCategoryNode> result = new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
//        System.out.println(result.toString());
        return result;
    }
}
