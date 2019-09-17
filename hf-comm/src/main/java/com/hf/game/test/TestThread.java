package com.hf.game.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 123 on 2019-7-1.
 */
public class TestThread {
    static final int COUNT=20;
    static CountDownLatch ctl=new CountDownLatch(COUNT);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Teacher(ctl)).start();
        Thread.sleep(1);
        for(int i=0;i<COUNT;i++) {
            new Thread(new Student(ctl, i)).start();
        }
//        synchronized (TestThread.class) {
//            TestThread.class.wait();
//        }

    }
}
