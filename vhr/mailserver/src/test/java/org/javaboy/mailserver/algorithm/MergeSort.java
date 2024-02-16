package org.javaboy.mailserver.algorithm;

import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid || j <= end) {
            if (i > mid) {
                tmp[k++] = arr[j++];
                continue;
            }
            if (j > end) {
                tmp[k++] = arr[i++];
                continue;
            }
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        for (i = 0; i < k; i++) {
            arr[start + i] = tmp[i];
        }

        tmp = null;
    }

    private static void mergeTree(int[] arr, int start, int end) {
        if (start >= end || arr == null) {
            return;
        }
        int mid = (start + end) / 2;
        // 相当于二叉树后续遍历
        mergeTree(arr, start, mid);
        mergeTree(arr, mid+1, end);

        merge(arr, start, mid, end);
    }

    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        mergeTree(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
