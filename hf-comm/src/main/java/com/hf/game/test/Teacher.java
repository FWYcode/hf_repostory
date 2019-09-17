package com.hf.game.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 123 on 2019-7-1.
 */
public class Teacher implements Runnable {
    CountDownLatch ctl;

    public Teacher(CountDownLatch cdl) {
        this.ctl = cdl;

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"老师发卷子");
        try {
            ctl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("老师收卷子");
    }
}
