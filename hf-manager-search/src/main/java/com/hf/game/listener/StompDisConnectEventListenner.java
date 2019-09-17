package com.hf.game.listener;

import com.hf.game.utils.SocketSessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2019-8-26.
 */
/*@Component
public  class StompDisConnectEventListenner implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    SocketSessionMap sessionMap;
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        System.out.println(event.getMessage());
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(event.getMessage());
//        System.out.println(wrap.getNativeHeader("id"));
        String sessionId = wrap.getSessionId();
        switch (wrap.getCommand()) {
            case CONNECT:
//                System.out.println("上线"+userId);
//                sessionMap.registerSession(userId,sessionId);
                break;
            case DISCONNECT:
                System.out.println("下线");
//                sessionMap.removeSession(sessionId);
                System.out.println(sessionMap.onlineCount());

                break;
            case SUBSCRIBE:
//                System.out.println(userId);
                System.out.println("订阅");
                break;
            case SEND:
                System.out.println("发送");
            case UNSUBSCRIBE:
                System.out.println("取消订阅");
                break;
            default:
                break;
        }
    }
}*/
