package com.hf.game.action;

import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.modul.response.ResponseResult;
import com.hf.game.module.items.CateGoryResult;
import com.hf.game.module.items.TestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.Temporal;
import java.util.Map;

/**
 * Created by 123 on 2019-9-2.
 */
@RequestMapping("search/test/item")
@RestController
public class TestRibbonAction {
    @Autowired
    RestTemplate template;
    @GetMapping("all")
    public ResponseResult<TestItem> test(){
        ResponseEntity<ResponseResult> entity = template.getForEntity("http://ITEM-SERVER/item/test/all", ResponseResult.class);
        System.out.println(entity.getBody());
        return entity.getBody();
    }

    @GetMapping("page/sort")
    public ResponseResult<TestItem> pageSort() {
        ResponseEntity<QueryResponseResult> forEntity = template.getForEntity("http://ITEM-SERVER/item/test/allpagesort", QueryResponseResult.class);
        return forEntity.getBody();
    }
}
