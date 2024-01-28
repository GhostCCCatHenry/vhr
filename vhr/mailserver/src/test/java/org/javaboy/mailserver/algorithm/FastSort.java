package org.javaboy.mailserver.algorithm;

import java.util.Arrays;

public class FastSort {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int tmp = nums[start];
            int low = start - 1; // 给do while预备
            int high = end + 1;
            while(low < high) {
                do low++ ; while (nums[low] < tmp);
                do high-- ; while (nums[high] > tmp);
                if(low < high) { // 在内层选择迭代后，需要交换位置
                    swap(nums, low, high);
                }
            }
            quickSort(nums, high+1, end);
            quickSort(nums, start, high);
        }
    }
    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
