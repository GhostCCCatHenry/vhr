package org.javaboy.mailserver.concurrent;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedPrint {

    private static synchronized void method() {}

    public static void main(String[] args) throws Exception {
//        ReentrantLock lock = new ReentrantLock();
        // 交替打印
        Thread jobA = new Thread(() -> {
            synchronized (SynchronizedPrint.class) {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println("A Thread: " + i);
                        try {
                            SynchronizedPrint.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        SynchronizedPrint.class.notify();
                    }
                }
            }
        });
        Thread jobB = new Thread(() -> {
            synchronized (SynchronizedPrint.class) {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 1) {
                        System.out.println("B Thread: " + i);
                        try {
                            SynchronizedPrint.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        SynchronizedPrint.class.notify();
                    }
                }
            }
        });
        jobA.start();
        jobB.start();
        jobA.join();
        jobB.join();
        for (int i = 0; i <= 100; i++) {
            System.out.println("Main Thread: " + i);
        }
    }

    /*
    callable<T> 接口需要FutureTask<T>类来装载
    runnable 接口需要Thread来装载
    Thread不需要装载
     */
    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("do some things");
            return "over";
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("do some things");
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("do some things");
        }
    }
}
