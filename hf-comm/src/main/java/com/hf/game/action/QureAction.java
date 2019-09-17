package com.hf.game.action;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.hf.game.modul.dto.request.TbItemPageQuery;
import com.hf.game.modul.pojo.TbItem;
import com.hf.game.modul.dto.response.ResponsResult;
import com.hf.game.service.TbitemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 123 on 2019-5-23.
 */
@RestController
@RequestMapping("find")
public class QureAction {
    @Autowired
    TbitemServer tbitemServer;

    @GetMapping("page/list/{pageNum}/{pageSize}")
    public ResponsResult<TbItem> quireListPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, TbItemPageQuery pageQuery) {

        Page<TbItem> tbItems = tbitemServer.findAll(pageNum,pageSize,pageQuery);
        ResponsResult<TbItem> rsult = new ResponsResult<>();
        rsult.setCode(200);
        rsult.setData(tbItems.getResult());
        rsult.setCount(tbItems.getTotal());
        rsult.setMsg("sucess");
        String jsonString = JSON.toJSONString(rsult);
        return rsult;
    }

    @GetMapping("{id}")
    public ResponsResult<?> quireOne(@PathVariable(value = "id") Long id) {
        List<TbItem> tbItem = tbitemServer.findById(id);
        ResponsResult<TbItem> result = new ResponsResult<>();
        result.setCode(0);
        result.setMsg("sucess");
        result.setData(tbItem);
        return result;
    }

}
