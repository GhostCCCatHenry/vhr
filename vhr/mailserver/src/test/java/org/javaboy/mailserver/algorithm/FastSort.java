package org.javaboy.mailserver.algorithm;

import java.util.Arrays;

public class FastSort {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int basicNum = nums[start];
        int low = start - 1;
        int high = end + 1;
        while (low < high) {
            // 此处low会把基准值也给交换走。
            do high --; while (nums[high] > basicNum);
            do low ++; while (nums[low] < basicNum);
            if (low <= high) {
                int temp  = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
            }
        }
        // 此时high是最后一个小于基准值的, 不能用low，因为low很有可能是大于基准值的，会不准。
        quickSort(nums, start, high);
        quickSort(nums, high+1, end);
    }
    public static void main(String[] args) {
        int[] arr = {10,8,7,13,22,39,19,12,4,51,5,6};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
//    if (start < end) {
//        int tmp = nums[start];
//        int low = start - 1; // 给do while预备
//        int high = end + 1;
//        while(low < high) {
//            do low++ ; while (nums[low] < tmp);
//            do high-- ; while (nums[high] > tmp);
//            if(low < high) { // 在内层选择迭代后，需要交换位置
//                swap(nums, low, high);
//            }
//        }
//        quickSort(nums, high+1, end);
//        quickSort(nums, start, high);
//    }
}
