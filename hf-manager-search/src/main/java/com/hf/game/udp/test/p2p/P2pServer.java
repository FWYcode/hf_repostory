package com.hf.game.udp.test.p2p;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2019-8-17.
 */
public class P2pServer {
    private List<WebSocket> socketPool = new ArrayList<>();

    public void init(int port) {
        final WebSocketServer socket = new WebSocketServer(new InetSocketAddress(port)) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                socketPool.add(webSocket);
                write(webSocket, "服务器就绪....");
                System.out.println("服务器准备就绪");

            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {
                socketPool.remove(webSocket);

            }

            @Override
            public void onMessage(WebSocket webSocket, String s) {

                System.out.println("收到:" +webSocket.getRemoteSocketAddress().getAddress().getHostAddress()+ "的消息" + s);
                write(webSocket, "ok");
            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {


            }

            @Override
            public void onStart() {
                System.out.println("服务端启动....");
            }
        };
        socket.start();
        System.out.println("监听端口:" + port);

    }

    private void write(WebSocket socket, String msg) {
        socket.send(msg);
    }

    public List<WebSocket> getSocketPool() {
        return socketPool;
    }

}
