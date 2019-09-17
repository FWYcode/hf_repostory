package com.hf.game.taskino;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class OnceTrigger implements Trigger {

    private Date startTime;

    private ScheduledFuture<?> future;

    public OnceTrigger() {}

    public OnceTrigger(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public TriggerType type() {
        return TriggerType.ONCE;
    }

    @Override
    public void parse(String s) {
        TimeFormat.SimplerDateFormat formatter = TimeFormat.ISOFormatter.take();
        this.startTime = formatter.parseQuitely(s);
    }

    @Override
    public String serialize() {
        TimeFormat.SimplerDateFormat formatter = TimeFormat.ISOFormatter.take();
        return formatter.format(this.startTime);
    }

    @Override
    public boolean schedule(ScheduledExecutorService scheduler, ExecutorService executor,
                    Predicate<Task> taskGrabber, Task task) {
        long delay = System.currentTimeMillis()-this.getStartTime().getTime();
        if (delay >= 0) {
            this.future = scheduler.schedule(() -> {
                executor.submit(() -> {
//                    boolean b= taskGrabber.test(task);
                    if (taskGrabber.test(task)) {
                        task.run();
                    }
                });
            }, 1000, TimeUnit.MILLISECONDS);
        }
        return this.future != null;
    }

    @Override
    public void cancel() {
        if (this.future != null) {
            this.future.cancel(false);
        }
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OnceTrigger)) {
            return false;
        }
        return this.startTime.equals(((OnceTrigger) other).startTime);
    }

}
