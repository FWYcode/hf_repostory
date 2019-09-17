package com.hf.game.udp.TCPUDPchat;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;

/**
 * Created by 123 on 2019-8-18.
 */
public class OnlineService implements Runnable{
    Socket socket;
    Map<String, Integer> users;

    public OnlineService() {
        // TODO Auto-generated constructor stub
    }

    public OnlineService(Socket socket, Map<String, Integer> users) {
        this.socket = socket;
        this.users = users;
    }

    @Override
    public void run() {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()){
            byte []buf = new byte[1024];
            int size = in.read(buf);
            String msg = new String(buf,0,size);

            //解析出msg中的nick信息
            String nick = msg.split(",")[0];
            int port = Integer.parseInt(msg.split(",")[1]);

            //将nick与端口放入在线列表中
            int port1 = socket.getPort();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            InetSocketAddress address = (InetSocketAddress) remoteSocketAddress;
            InetAddress addr = address.getAddress();
            users.put(nick, port);

            //String json = new Gson().toJson(users);
            String json = JSON.toJSONString(users);
            System.out.println(json);

            out.write(json.getBytes());
            out.flush();


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
