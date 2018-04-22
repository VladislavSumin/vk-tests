package ru.falseteam.vktests;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test implements Runnable {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new Test(), 1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdownNow();
        scheduledExecutorService.shutdown();
    }

    @Override
    public void run() {
        System.out.println("test");
    }
}
