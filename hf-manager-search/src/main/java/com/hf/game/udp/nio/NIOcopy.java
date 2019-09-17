package com.hf.game.udp.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by 123 on 2019-8-18.
 */
public class NIOcopy {


    public static void main(String[] args) throws IOException {
        nioCopyTest1();
        nioCopyTest2();
        nioCopyTest3();
    }

    /**
     * 通道之间的数据传输(直接缓冲区的模式)
     */
    private static void nioCopyTest3() throws IOException {
        long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("d:\\1.mkv"), StandardOpenOption.READ);

        FileChannel outChennel = FileChannel.open(Paths.get("d:\\13.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        outChennel.transferFrom(inChannel, 0, inChannel.size());

        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest3耗费时间:" + (end - startTime));
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    private static void nioCopyTest2() throws IOException {
        long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("d:\\1.mkv"), StandardOpenOption.READ);

        FileChannel outChennel = FileChannel.open(Paths.get("d:\\12.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        //内存映射文件(什么模式 从哪开始 到哪结束)
        MappedByteBuffer inMappeBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappeBuf = outChennel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接都缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappeBuf.limit()];
        inMappeBuf.get(dst);
        outMappeBuf.put(dst);

        inChannel.close();
        outChennel.close();
        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest2耗费时间:" + (end - startTime));
    }


    /**
     * 非直接缓冲区 文件的复制
     *
     * @throws IOException
     */
    private static void nioCopyTest1() throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\1.mkv"));
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\ 11.mkv");

        //获取通道
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChanel = fileOutputStream.getChannel();

        //分配缓冲区的大小
        ByteBuffer buf = ByteBuffer.allocate(1024*1024);

        //将通道中的数据存入缓冲区
        while (inChannel.read(buf) != -1) {
//            System.out.println(buf.position());
            buf.flip();//切换读取数据的模式
            outChanel.write(buf);
            buf.clear();
        }
        outChanel.close();
        inChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest1耗费时间:" + (end - startTime));
    }

}
