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
        List<String> arrayList = new ArrayList<>();


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
//        arrayList.trimToSize();

        Stack<String> stack = new Stack<>();




        /*
        实现了List、Deque（双端队列）、
        底层存储为Node：{ next, prev, element }, 为一个双向链表。
         */
        LinkedList<String> linkedList = new LinkedList<>();

        Queue<Integer> queue = new PriorityQueue<>();

        // 初始长度16，每次扩容*2，保证为2的n次幂
        HashMap<String, Object> hashMap = new HashMap<>();

        /**
         * 对HashMap hash值选取的解释：
         *
         * 一般hash表散列位置选取即为对数字num取模。
         * 在get方法中，使用了：
         * hash & (table.length-1) 这个看似是按位与，实际在table.length为 2指数幂时，这个算法实际为对数字取模。
         * 效率比%更高。
         * 实际为去找hash散列表的下标。
         *
         * 但是这种方法不好解决冲突，因为不够散列。
         * 所以使用hash() 方法增加散列度：
         * h ^ (h >> 16) -> hashcode()得到一个32位的hash值，先右移16位使得高位为0，再与hashcode抑或后，高16位不变。
         * 低16位在移位后将高16位填入。相当于用hashcode高16位抑或低16位。
         * 变为了抑或后的值（相同为0不同为1），此时取最低4位的值，作为hash散列表的填充位。
         *
         * 为什么右移16位？
         * 因为16位最大值位65536，散列表长度（length-1）很少能达到这么长。
         * 所以在预处理hash值时，只处理后16位，然后在处理冲突时，与长度进行按位与（取模）即可。
         *
         */


    }
}
