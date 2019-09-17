package com.hf.game.action;

import com.hf.game.module.Greeting;
import com.hf.game.module.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 123 on 2019-8-24.
 */
/*@Controller
public class WebSocketDemoAction {
    @MessageMapping("hello")
    @SendTo("/topic/greetings")
    public Greeting firet(HelloMessage message ) {
        System.out.println("收到："+message.toString()+"消息");
        return new Greeting("hello:"+ HtmlUtils.htmlEscape(message.getContent())+"!");
    }
}*/

