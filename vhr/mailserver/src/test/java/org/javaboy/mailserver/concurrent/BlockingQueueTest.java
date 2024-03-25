package org.javaboy.mailserver.concurrent;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueTest <T> {
    private DelayQueue<Delayed> delayQueue = new DelayQueue();

    private Queue<T> queue = new LinkedList<>();
    private PriorityQueue<T> sortedQueue;
    private ReentrantLock lock = new ReentrantLock();
    private final int capacity;
    private final Condition pollCondition;
    private final Condition offerCondition;

    public BlockingQueueTest(int capacity) {
        this.capacity = capacity;
        this.pollCondition = lock.newCondition();
        this.offerCondition = lock.newCondition();
    }

    public synchronized boolean offer(T item) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }
        boolean ret = queue.offer(item);
        notifyAll();
        return ret;
    }

    public synchronized T poll() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }
        T ret = queue.poll();
        notifyAll();
        return ret;
    }

}
