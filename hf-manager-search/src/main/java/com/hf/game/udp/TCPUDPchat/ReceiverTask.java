package com.hf.game.udp.TCPUDPchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by 123 on 2019-8-18.
 */
public class ReceiverTask implements Runnable {
    DatagramSocket socket = null;
    public ReceiverTask() {
        // TODO Auto-generated constructor stub
    }

    public ReceiverTask(DatagramSocket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        String msg = null;
        byte []buf = new byte[1024*8];
        do {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);

                byte []data = packet.getData();
                msg = new String(data,0,packet.getLength());
                System.out.println("收到: "+msg);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (!msg.equalsIgnoreCase("bye"));
        System.out.println("接收端关闭");
    }
}
