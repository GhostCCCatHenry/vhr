package org.javaboy.mailserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class test {
    public static void main(String[] args) {
        /*
        ArrayList底层存储为: Object[] elementData
        1.如果使用无参构造，则会在初次add数据的时候，默认创建一个长度为10的数组（DEFAULT_CAPACITY）。
        2.使用指定initialCapacity参数构造，直接创建一个长度相等的数组。
        3.使用入参为Collection类型的构造方法，会拷贝Collection.toArray数组

        ensureCapacity 手动指定扩容。
        ensureExplicitCapacity
         */
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();

        Queue<Integer> queue = new PriorityQueue<>();

        /*
        ensureCapacityInternal：
         */
        arrayList.add("test1");


    }
}
