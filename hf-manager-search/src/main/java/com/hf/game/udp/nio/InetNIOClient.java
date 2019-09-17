package com.hf.game.udp.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 123 on 2019-8-19.
 */
public class InetNIOClient {
    private static Selector selector;

    public static void init() throws IOException{
        selector = Selector.open();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8099));

        sc.register(selector, SelectionKey.OP_CONNECT);
    }

    public static void listen() throws IOException{
        while(true){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while(it.hasNext()){
                SelectionKey key = it.next();
                it.remove();

                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    if(channel.finishConnect()){
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    }
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
        channel.write(ByteBuffer.wrap("client write".getBytes()));

    }

    private static void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = channel.read(buffer);
        byte[] bytes = new byte[count];
        buffer.flip();
        buffer.get(bytes);
        System.out.println(new String(bytes));

    }

    public static void main(String[] args) throws IOException {
        init();
        listen();
    }
}
