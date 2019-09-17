package com.hf.game.listener;

import com.hf.game.utils.SocketSessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Created by 123 on 2019-8-26.
 */
/*@Component
public class StompConnectEventListenner implements ApplicationListener<SessionConnectEvent> {
    @Autowired
    SocketSessionMap sessionMap;
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        System.out.println(event);
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(event.getMessage());
        String userId = wrap.getFirstNativeHeader("id");
        String sessionId = wrap.getSessionId();
        switch (wrap.getCommand()) {
            case CONNECT:
                System.out.println("上线"+userId);
                sessionMap.registerSession(sessionId,userId);
                break;
            case DISCONNECT:
                System.out.println("下线");
                sessionMap.removeSession(userId);
                break;
            case SUBSCRIBE:
                System.out.println(userId);
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
