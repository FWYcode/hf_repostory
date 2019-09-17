package com.hf.game.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 123 on 2019-5-23.
 */
@Controller
@RequestMapping("test")
public class CommAction {
    @RequestMapping ("{name}")
    public String dosomthing(@PathVariable("name")String name) {
        return name;
    }
}
