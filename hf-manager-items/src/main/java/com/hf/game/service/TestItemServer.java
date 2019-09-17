package com.hf.game.service;

import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.TestItem;

/**
 * Created by 123 on 2019-9-3.
 */
public interface TestItemServer {
    ResponseResult<TestItem> queryAllPageSort();

    ResponseResult<TestItem> queryByName(String name);

    ResponseResult<TestItem> queryAll();
}
