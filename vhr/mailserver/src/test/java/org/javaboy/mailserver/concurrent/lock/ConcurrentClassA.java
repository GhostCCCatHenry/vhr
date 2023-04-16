package org.javaboy.mailserver.concurrent.lock;

public class ConcurrentClassA {

    public static void main(String[] args) {
        ConcurrentClassB<String> concurrentClassB = new ConcurrentClassB<>();

        ConcurrentClassA concurrentClassA = new ConcurrentClassA();

        synchronized (concurrentClassA) {
            System.out.println("ddddd");
        }

        synchronized (concurrentClassB) {
            System.out.println("ddddd");
        }
    }

}
