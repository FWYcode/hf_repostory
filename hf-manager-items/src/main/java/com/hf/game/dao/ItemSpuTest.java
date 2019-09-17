package com.hf.game.dao;

import com.hf.game.dao.jparepostory.ItemSpuRepostory;
import com.hf.game.module.items.ItemSPU;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 123 on 2019-9-1.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class  ItemSpuTest {
    @Autowired
    ItemSpuRepostory repostory;
    @Test
    public void test1(){
        List<ItemSPU> all = repostory.findAll();
        System.out.println(all);
    }
}
