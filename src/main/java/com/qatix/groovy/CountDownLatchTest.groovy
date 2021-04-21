package com.qatix.groovy

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch called = new CountDownLatch(1)
        Timer timer = new Timer()
        timer.schedule(new TimerTask() {
            @Override
            void run() {
                called.countDown()
            }
        }, 0)
        assert called.await(10, TimeUnit.SECONDS)
        println("done")
    }
}
