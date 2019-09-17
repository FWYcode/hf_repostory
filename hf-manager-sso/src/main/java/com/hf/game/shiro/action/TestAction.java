package com.hf.game.shiro.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 123 on 2019-6-5.
 */
@Controller
@RequestMapping("user")
public class TestAction {
    @RequestMapping("show")
    public String test(HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        return "user/show";
    }

    @RequestMapping("add")
    public String doAdd(HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        return "user/add";
    }

    @RequestMapping("delete")
    public String dele() {
        return "user/delete";
    }
}
