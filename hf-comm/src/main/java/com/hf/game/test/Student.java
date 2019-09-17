package com.hf.game.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 123 on 2019-7-1.
 */
public class Student implements Runnable {
    CountDownLatch ctl;
    int num;

    public Student(CountDownLatch ctl, int num) {
        this.ctl=ctl;
        this.num=num;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"学生"+num+"写卷子");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"学生"+num+"交卷子");
        ctl.countDown();
    }
}
