package com.hf.game.udp.nio;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 123 on 2019-8-19.
 */
public class InetNIOServer {
    private static Selector selector ;

    public  static void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8099));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void listen() throws IOException{
        while(true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
//            System.out.println(selectedKeys.size());
            while(it.hasNext()){
                SelectionKey key = it.next();
                // 由于select操作只管对selectedKeys进行添加，所以key处理后我们需要从里面把key去掉
                System.out.println(JSON.toJSONString(selectedKeys));
                it.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    // 得到与客户端的套接字通道
                    SocketChannel channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    // 同样将于客户端的通道在selector上注册，,可以通过key获取关联的选择器
                    channel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);

                }else if(key.isReadable()){
                    read(key);
                    // 改变自身关注事件，可以用位或操作|组合时间
                    key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                }else if(key.isWritable()){
                    write(key);
                    // 改变自身关注事件，可以用位或操作|组合时间
                    key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                }
            }
        }
    }

    private static void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(ByteBuffer.wrap("server write".getBytes()));

    }

    private static void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = channel.read(buffer);
        byte[] bytes = new byte[count];
        buffer.flip();
        buffer.get(bytes);
//        System.out.println(new String(bytes));

    }

    public static void main(String[] args) throws IOException {
        init();
        listen();
    }


}
