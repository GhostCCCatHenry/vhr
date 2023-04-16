package org.javaboy.mailserver.concurrent.lock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// 同步方法
public class ConcurrentClassB<T> {

    private final List<T> list = new ArrayList<>();

    public synchronized void addList(List<T> list){
        if (getLength() > 0) {
            this.list.addAll(list);
        }
    }

    public synchronized void addList(T element){
        list.add(element);
        System.out.println(getLength());
    }

    private synchronized Integer getLength(){
        return this.list.size();
    }

    public synchronized List<T> getList(){
        return this.list;
    }
}
