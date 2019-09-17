package com.hf.game;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hf.game.dao.ItemRepository;
import com.hf.game.dao.MyMapper;
import com.hf.game.modul.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 123 on 2019-5-28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClusterTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ItemRepository repository;
    @Autowired
    MyMapper myMapper;

    @Test
    public void test1() {
        List<TbItem> all = repository.findAll();
        for (TbItem tbItem : all) {
            redisTemplate.opsForValue().set(tbItem.getId().toString(), tbItem);
        }
    }

    @Test
    public void test2() {
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("redis" + keys.size());
        int all = repository.findAll().size();
        System.out.println("mysql" + all);
    }

    @Test
    public void test3() {
        List<TbItem> all = repository.findAll();
        List<String> ids = new ArrayList<>();
        for (TbItem tbItem : all) {
            ids.add(tbItem.getId().toString());
        }
        redisTemplate.delete(ids);
        redisTemplate.keys("*");
    }

    @Test
    public void test4() {
        int i = 0;
        while (true) {
            if (i > 10000) {
                break;
            }
            redisTemplate.opsForValue().set("" + i, i);
            i++;
        }
    }

    @Test
    public void test5() {


        Page<TbItem> tbItems = PageHelper.startPage(1, 2).doSelectPage(()->myMapper.selectAll());

        System.out.println(tbItems);
    }

    @Test
    public void test6() {
        myMapper.selectAll();
    }


}
