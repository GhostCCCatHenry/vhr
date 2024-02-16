package org.javaboy.mailserver.algorithm;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {20,30,90,40,70,110,60,10,100,50,80,6,7,19};

//        sortAsc(nums);
        sortDesc(nums);
    }

    // 最小堆
    private static void sortDesc(int[] nums) {
        // 二叉树 i 的左子节点为 2i+1；右子节点为2i+2；父节点为 向下取整[(i-1)/2]
        // 最后一个节点的父节点开始。第一次从底向上，可以保证底下子树的一定大余上边的。
        for (int i=((nums.length-1)-1)/2; i>=0; i--) {
            balanceMinHeap(nums, i, nums.length-1);
        }
        int tmp;
        // 每次只跟最上边的换
        for (int i=nums.length-1; i>0; i--) {
            tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            balanceMinHeap(nums, 0, i-1);
        }

        System.out.println(Arrays.toString(nums));

    }

    // start 位置或者向下有大值，透传到底层。
    private static void balanceMinHeap(int[] nums, int start, int end) {
        int i = start;
        int l = 2*start + 1;
        int tmp = nums[i];

        // 因为有可能有右子节点不存在的情况。
        for (; l<=end; i=l, l=2*l+1) {
            if (l < end && nums[l+1] < nums[l]) {
                l++;
            }
            // 从底向上，可以保证底下子树的一定大余上边的。
            if (tmp <= nums[l]) {
                break;
            }
            else {
                nums[i] = nums[l];
                nums[l] = tmp;
            }
        }
    }

    // 最大堆
    private static void sortAsc(int[] nums) {
        Map<String, String> ht = new Hashtable<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        // 二叉树 i 的左子节点为 2i+1；右子节点为2i+2；父节点为 向下取整[(i-1)/2]
        // 最后一个节点的父节点开始。第一次从底向上，可以保证底下子树的一定小余上边的。
        for (int i=((nums.length-1)-1)/2; i>=0; i--) {
            balanceMaxHeap(nums, i, nums.length-1);
        }
        int tmp;
        // 每次只跟最上边的换
        for (int i=nums.length-1; i>0; i--) {
            tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            balanceMaxHeap(nums, 0, i-1);
        }

        System.out.println(Arrays.toString(nums));

    }

    // start 位置或者向下有小值，透传到底层。
    private static void balanceMaxHeap(int[] nums, int start, int end) {
        int i = start;
        int l = 2*start + 1;
        int tmp = nums[i];

        // 因为有可能有右子节点不存在的情况。
        for (;l<=end; i=l, l=2*l+1) {
            if (l < end && nums[l+1] > nums[l]) {
                l++;
            }
            // 从底向上，可以保证底下子树的一定小余上边的。
            if (tmp >= nums[l]) {
                break;
            }
            else {
                nums[i] = nums[l];
                nums[l] = tmp;
            }
        }
    }

}
