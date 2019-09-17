package com.hf.game.udp.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

/**
 * Created by 123 on 2019-8-14.
 */
public class UdpSocket {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        File file = null;
        FileInputStream stream = null;
        try {
            socket = new DatagramSocket(8081);
            file = new File("d:/test/1.mp4");
            stream = new FileInputStream(file);
            String msg = "end";
            byte[] msgs = msg.getBytes();
            byte[] bytes = new byte[1024];
            byte[] data = new byte[8 * 1024];
            int len = 0;
            packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8080);
            long star = System.currentTimeMillis();
            while ((len = stream.read(data)) > -1) {
                packet.setData(data);
                socket.send(packet);
                socket.receive(packet);
            }
            packet.setData(msgs);
            socket.send(packet);

           long nd= System.currentTimeMillis();
            System.out.println(nd-star);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
