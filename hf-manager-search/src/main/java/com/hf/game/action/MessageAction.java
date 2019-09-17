package com.hf.game.action;

import com.hf.game.module.HelloMessage;
import com.hf.game.utils.SocketSessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * Created by 123 on 2019-8-26.
 */
/*
@Controller
public class MessageAction {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private SocketSessionMap sessionMap;
    @MessageMapping("/chat")
    public void sayHello(HelloMessage msg) {
        System.out.println(msg);
        String pid = String.valueOf(msg.getPid());
        String id = String.valueOf(msg.getId());
        String sendTo = "/topic/chat/" + pid;
        String content = msg.getId() + ":" + msg.getContent();
        if (sessionMap.getUserSessionId(pid)!=null) {
            messagingTemplate.convertAndSend(sendTo, HtmlUtils.htmlEscape(content));
        }else {
            sendTo="/topic/chat/"+id;
            content = "对方已下线";
            messagingTemplate.convertAndSend(sendTo,HtmlUtils.htmlEscape(content));
        }

    }
    @RequestMapping("/chat/{id}")
    public String chatPage(@PathVariable(value = "id") int id, ModelMap model) {
        model.addAttribute("id", id);
        int count = sessionMap.onlineCount();
        model.addAttribute("count", count);
        return "chat";
    }

    @MessageMapping("/chatOut/")
    public void chaOut(String uid) {
    }
}*/
