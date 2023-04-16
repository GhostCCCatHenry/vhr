package org.javaboy.mailserver.concurrent.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SynchronizedDemo {
    private static final ConcurrentClassB<String> concurrentClassB = new ConcurrentClassB<>();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new MyThread();
        System.out.println("start main thread");
        concurrentClassB.addList("ddw");

        thread1.start();
        thread2.start();

        concurrentClassB.getList().forEach(System.out::println);
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("start runnable thread");
            concurrentClassB.addList("word");
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("start my thread");
            List<String> list = new ArrayList<>();
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");

            concurrentClassB.addList(list);
        }
    }
}
