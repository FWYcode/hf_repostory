package com.hf.game.udp.test.p2p;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2019-8-17.
 */
public class P2pClient {
    private List<WebSocket> sockets = new ArrayList<>();

    public List<WebSocket> getSockets() {
        return sockets;
    }

    public void connectPeer(String peer) {
        try {
            final WebSocketClient socketClient=new WebSocketClient(new URI(peer)) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    write(this, "clienOK111");
                    sockets.add(this);
                }

                @Override
                public void onMessage(String s) {
                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    sockets.remove(this);
                }

                @Override
                public void onError(Exception e) {
                    System.out.println(e.getMessage());
                    sockets.remove(this);
                }
            };
            socketClient.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void write(WebSocket client, String clienOK) {
        client.send(clienOK);
    }
}
