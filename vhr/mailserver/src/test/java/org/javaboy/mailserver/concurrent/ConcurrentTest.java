package org.javaboy.mailserver.concurrent;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.concurrent.Callable;

public class ConcurrentTest {

    public static void main(String[] args) throws Exception {


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
