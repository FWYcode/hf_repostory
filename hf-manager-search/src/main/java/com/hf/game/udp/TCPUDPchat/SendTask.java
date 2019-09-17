package com.hf.game.udp.TCPUDPchat;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 123 on 2019-8-18.
 */
public class SendTask implements Runnable {
    HashMap<String, Integer> users;
    DatagramSocket socket = null;
    Socket tcpSocket;

    public SendTask() {
        // TODO Auto-generated constructor stub
    }

    public SendTask(DatagramSocket socket, HashMap<String, Integer> users, Socket tcpSocket) {
        this.socket = socket;
        this.users = users;
        this.tcpSocket = tcpSocket;
    }

    @Override
    public void run() {
        String msg = null;
        Scanner cin = new Scanner(System.in);
        DatagramPacket packet = null;
        while (true) {
            GetUsersFromServer();
            System.out.println("输入接收方:");
            String nick = cin.nextLine();
            if (users.containsKey(nick)) {
                do {
                    int port = users.get(nick);
                    System.out.print("发送：");
                    msg = cin.nextLine();
                    byte[] data = msg.getBytes();
                    try {

                        // 创建数据包
                        packet = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), port);

                        // 发送
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } while (!msg.equalsIgnoreCase("bye"));
                System.out.println("发送结束");
            } else {
                System.out.println("该用户不在线");
            }
        }
    }

    private void GetUsersFromServer() {
        // TODD C/S
        try {
            tcpSocket = new Socket("127.0.0.1", 9000);
            InputStream in = tcpSocket.getInputStream();
            OutputStream out = tcpSocket.getOutputStream();
            // 发送自己的昵称和端口
            int udpPort = socket.getLocalPort();
            String ss = new String("######" + "," + udpPort);

            out.write(ss.getBytes());
            out.flush();

            // 接收在线用户的列表
            byte[] buf = new byte[1024];
            int size = in.read(buf);
            String json = new String(buf, 0, size);
            // HashMap<String, Integer> users = new Gson().fromJson(json,HashMap.class);
            users = JSON.parseObject(json, HashMap.class);
            System.out.println("在线列表 :  " + users);
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
