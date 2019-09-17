package com.hf.game.action;

import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.TestItem;
import com.hf.game.service.TestItemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2019-9-3.
 */
@RestController
@RequestMapping("item/test")
public class ItemTestAction {
    @Autowired
    TestItemServer itemServer;

    @GetMapping("allpagesort")
    public ResponseResult<TestItem> findAllpage() {
        ResponseResult<TestItem> result = itemServer.queryAllPageSort();
        System.out.println(result);
        return result;
    }

    @GetMapping("all")
    public ResponseResult<TestItem> findAll() {
        ResponseResult<TestItem> result = itemServer.queryAll();
        return result;
    }

    @GetMapping("by/{name}")
    public ResponseResult<TestItem> findByName(@PathVariable("name") String name) {
        return itemServer.queryByName(name);
    }
}
