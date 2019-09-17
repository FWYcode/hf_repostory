package com.hf.game.udp.TCPUDPchat;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 123 on 2019-8-18.
 */
public class Chat {
    Thread sender;
    Thread receiver;

    DatagramSocket udpSocket;
    Socket tcpSocket;

    public Chat() {
        try {
            Scanner cin = new Scanner(System.in);
            System.out.println("昵称：");
            String nick = cin.nextLine();

            //TODD C/S
            tcpSocket = new Socket("192.168.0.121",9000);
            InputStream in = tcpSocket.getInputStream();
            OutputStream out = tcpSocket.getOutputStream();

            //发送自己的昵称和端口
            udpSocket = new DatagramSocket();
            int udpPort = udpSocket.getLocalPort();
            String msg = new String(nick+","+udpPort);

            out.write(msg.getBytes());
            out.flush();

            //接收在线用户的列表
            byte []buf = new byte[1024];
            int size = in.read(buf);
            String json = new String(buf,0,size);
            //HashMap<String, Integer> users = new Gson().fromJson(json,HashMap.class);
            HashMap<String, Integer> users = JSON.parseObject(json, HashMap.class);
            System.out.println("在线列表 :  "+users);

            //double p = users.get(nick).doubleValue();

            //把用户列表传给SenderTask
            sender = new Thread(new SendTask(udpSocket,users,tcpSocket));
            receiver = new Thread(new ReceiverTask(udpSocket));
            sender.start();
            receiver.start();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Chat chat = new Chat();
    }


}
