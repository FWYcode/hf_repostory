package com.hf.game.udp.TCPUDPchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 123 on 2019-8-18.
 */
public class ChatServer {
    ServerSocket serverSocket;
    int serverPort = 9000;
    ExecutorService pool;
    Map<String, Integer> users = new HashMap<String, Integer>();

    public ChatServer() {
        pool = Executors.newCachedThreadPool();
    }

    public void start() {
        System.out.println("服务器启动：。。。。");
        try {
            serverSocket = new ServerSocket(serverPort);
            while(true) {
                Socket socket = serverSocket.accept();
                OnlineService onlineService = new OnlineService(socket,users);
                pool.execute(onlineService);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
