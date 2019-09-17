package com.hf.game.service.impl;

import com.hf.game.dao.jparepostory.TestItemRepostoey;
import com.hf.game.modul.response.CommonCode;
import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.modul.response.QueryResult;
import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.TestItem;
import com.hf.game.service.TestItemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2019-9-3.
 */
@Service
public class TestItemServerImpl implements TestItemServer {
    @Autowired
    TestItemRepostoey repostoey;

    @Override
    public ResponseResult<TestItem> queryAllPageSort() {
        Page<TestItem> all = repostoey.findAll(PageRequest.of(0, 1000, new Sort(Sort.Direction.ASC, "id")));
        List<TestItem> list = all.getContent();
        QueryResult<TestItem> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(Integer.toUnsignedLong(list.size()));
        QueryResponseResult<TestItem> responseResult = new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
        return responseResult;
    }

    @Override
    public ResponseResult<TestItem> queryByName(String name) {
        List<TestItem> result = repostoey.findByItemName(name);
        QueryResult<TestItem> result1 = new QueryResult<>();
        result1.setTotal(Integer.toUnsignedLong(result.size()));
        result1.setList(result);
        ResponseResult<TestItem> result2 = new QueryResponseResult<>(CommonCode.SUCCESS, result1);
        return result2;
    }

    @Override
    public ResponseResult<TestItem> queryAll() {
        List<TestItem> all = repostoey.findAll();
        QueryResult<TestItem> queryResult = new QueryResult<>();
        queryResult.setList(all);
        queryResult.setTotal(Integer.toUnsignedLong(all.size()));
        QueryResponseResult<TestItem> responseResult = new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
        return responseResult;
    }
}
