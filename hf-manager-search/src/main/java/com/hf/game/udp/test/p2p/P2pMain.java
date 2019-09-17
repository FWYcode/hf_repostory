package com.hf.game.udp.test.p2p;

/**
 * Created by 123 on 2019-8-17.
 */
public class P2pMain {
    public static void main(String[] args) {
//        P2pServer server=new P2pServer();
        P2pClient client=new P2pClient();
//        server.init(8888);
        client.connectPeer("ws://192.168.0.128:18000");
    }

}
