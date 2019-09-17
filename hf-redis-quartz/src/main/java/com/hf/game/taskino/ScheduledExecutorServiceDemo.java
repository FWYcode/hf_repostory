package com.hf.game.taskino;

import it.sauronsoftware.cron4j.SchedulingPattern;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 123 on 2019-9-6.
 */
public class ScheduledExecutorServiceDemo {
    static ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar);
        Date date =new Date();
//        calendar.setTime(date);
        /*SchedulingPattern pattern = new SchedulingPattern("*//*3 * * * *");
        boolean match = pattern.match(date.getTime());
        System.out.println(match);
        System.out.println(date.getTime());
        System.out.println(calendar.getTimeInMillis());*/

        scheduled.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis());
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}
