package com.hf.game;

import com.hf.game.mapper.PowerMapper;
import com.hf.game.mapper.UserMapper;
import com.hf.game.module.pojo.userManager.Power;
import com.hf.game.module.pojo.userManager.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 123 on 2019-6-4.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test2 {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PowerMapper powerMapper;
    @Value("${shiroServerUrlPrefix}")
    String url;
    @Test
    public void ts2(){
        System.out.println(url);
    }
    @Test
    public void test1() {
        User user = userMapper.selectById(1l);
        System.out.println(user);

    }

    @Test
    public void test2() {
        Power power = powerMapper.selectById(1l);
        System.out.println(power);
    }
}
