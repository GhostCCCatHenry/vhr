package org.javaboy.mailserver.concurrent;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedPrint {
    private static final Object lock = new Object();
    private static int nums = 0;

    /**
     * 两个线程交替打印
     * @param start
     */
    private static void method(int start) {
        for (int i = 0; i <= 100; i++) {
            synchronized (lock) {
                if (i % 2 == start) {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " print " + i);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                } else {
                    lock.notify();
                }
            }
        }
    }

    /**
     * 多线程交替打印，主要练习notifyAll
     * @param start
     */
    private static void method2(int start) {
        final int maxNum = 10; // 限制次数
        for (int i = 0; i <= 10; i++) {
            synchronized (lock) {
                while (nums % 3 != start) {
                    try {
                        lock.wait(); // 一直轮询wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                nums++;
                System.out.println("Thread: " + Thread.currentThread().getName());
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        ReentrantLock lock = new ReentrantLock();

        // 交替打印
        Thread jobA = new Thread(() -> {
            method2(0);
        }, "A");
        Thread jobB = new Thread(() -> {
            method2(1);
        }, "B");

        Thread jobC = new Thread(() -> {
            method2(2);
        }, "C");
        jobA.start();
        jobB.start();
        jobC.start();
//        for (int i = 0; i <= 100; i++) {
//            System.out.println("Main Thread: " + i);
//        }
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
