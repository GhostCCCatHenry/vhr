package org.javaboy.mailserver.collections;

import java.util.*;

public class CollectionsTest {
    public static void main(String[] args) {
        /*
        ArrayList底层存储为: Object[] elementData
        1.如果使用无参构造，则会在初次add数据的时候，默认创建一个长度为10的数组（DEFAULT_CAPACITY）。
        2.使用指定initialCapacity参数构造，直接创建一个长度相等的数组。
        3.使用入参为Collection类型的构造方法，会拷贝Collection.toArray数组

        ensureCapacity 手动指定扩容。
        ensureExplicitCapacity-> grow: 扩容到 1.5倍 与 指定大小 比较下更大的容量。
         */
        ArrayList<String> arrayList = new ArrayList<>();

        /*
        ensureCapacityInternal：
        此时modCount会++。modCount：发现fail-fast的有效手段。
        grow
         */
        arrayList.add("test1");
        arrayList.add("test2");

        /*
        arrayCopy: 原数组，源数组需要复制的起始位置，目标数组，目标数组放置的位置，原数组拿出多少个来放到目标中
        System.arrayCopy(elementData, 2, elementData, 3, arrayList.size()-2)
        此处由于rangeCheckForAdd存在，如果不加test2，会报错。
         */
        arrayList.add(2, "test1");
        /*
        add的逆过程，值得关注的是，elementData在复制后，会取消掉数组最后一位的引用。
        但是不会缩容！
         */
        arrayList.remove(1);

        /*
        缩容，modCount++，注意，对ArrayList进行的写操作均会使得mod++，为了应对并发问题（并发情况modCount必然出现异变）。
         */
        arrayList.trimToSize();




        /*
        实现了List、Deque（双端队列）、
        底层存储为Node：{ next, prev, element }, 为一个双向链表。
         */
        LinkedList<String> linkedList = new LinkedList<>();

        Queue<Integer> queue = new PriorityQueue<>();

        HashMap<String, Object> hashMap = new HashMap<>();



    }
}
