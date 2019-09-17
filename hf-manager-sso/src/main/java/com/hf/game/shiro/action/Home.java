package com.hf.game.shiro.action;

import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 123 on 2019-6-6.
 */
@Controller
@RequestMapping("error")
public class Home {
    @GetMapping("{index}")
    public String a(@PathVariable("index")String index) {
       return "error/"+index;

    }
}
