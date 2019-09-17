package com.hf.game.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 123 on 2019-8-26.
 */
@Component
public class SocketSessionMap {
    private final static ConcurrentHashMap<String, String> sessionMap = new ConcurrentHashMap<>();

    public void registerSession(String userId, String session) {
        sessionMap.put(userId, session);
    }

    public void removeSession(String userId) {
        sessionMap.remove(userId);
    }

    public String getUserSessionId(String userId) {
        return sessionMap.get(userId);
    }

    public Map<String, String> queryAllSession() {
        return sessionMap;
    }

    public int onlineCount() {
        return sessionMap.size();
    }
}
