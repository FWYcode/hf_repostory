package com.hf.game.udp.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * Created by 123 on 2019-8-15.
 */
public class Revic {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        File file = null;
        DatagramPacket packet = null;
        OutputStream outputStream = null;
        try {
            socket = new DatagramSocket(8080);
            file = new File("D:/test/3");
            if (!file.exists()) {
                file.mkdirs();
            }

            outputStream = new FileOutputStream(new File(file.getPath() + "/1.mp4"));
            byte[] buf = new byte[8 * 1024];
            packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 8081);
            int len = 0;
            String ok = "ok";
            byte[] oks = ok.getBytes();
            int i = 0;
            long star = System.currentTimeMillis();
            while (true) {
                socket.receive(packet);
                byte[] data = packet.getData();
                String content=new String(data,0,packet.getLength());
                if (content.equals("end")) {
                    outputStream.close();
                    socket.close();
                    break;
                }
                SocketAddress address = packet.getSocketAddress();
                InetSocketAddress inetAddress = (InetSocketAddress) address;
                String address1 = inetAddress.getAddress().getHostAddress();
                int port = inetAddress.getPort();
//                System.out.println("ip:"+address1+"....port:"+port);
                outputStream.write(data, 0, data.length);
                packet.setData(oks);
//                packet.setLength(oks.length);
                socket.send(packet);
                packet.setData(buf);
                packet.setLength(buf.length);
            }
            long endt = System.currentTimeMillis();
            System.out.println(endt-star);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
