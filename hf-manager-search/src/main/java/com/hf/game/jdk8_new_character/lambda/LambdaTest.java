package com.hf.game.jdk8_new_character.lambda;


import com.alibaba.fastjson.JSON;
import com.hf.game.module.items.CateGoryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by 123 on 2019-8-20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LambdaTest {
    @Autowired
    RestTemplate template;

    @Test
    public void test() {
        ResponseEntity<Map> forEntity = template.getForEntity("http://ITEM_SERVER/item/category/all", Map.class);
        Map body = forEntity.getBody();
        String data = JSON.toJSONString(body.get("data"));
        System.out.println(data);
        List<CateGoryResult> list = JSON.parseArray(data, CateGoryResult.class);
    }


}
