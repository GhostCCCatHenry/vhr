package org.javaboy.mailserver.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class bd {
    public static void main(String[] args) {
        int target = 23131;
        int[] digital = new int[] {2, 4, 9};
        int lenD = digital.length;

        Arrays.sort(digital);
        System.out.println(Arrays.toString(digital));
        String targetStr = String.valueOf(target);
        int lenT = targetStr.length();
        int minNum = -1;
        int i = 0;
        for (; i < targetStr.length(); i++) {
            int num = Character.getNumericValue(targetStr.charAt(i));
            minNum = search(digital, num);
            if (minNum == -1 || digital[minNum] < num) {
                break;
            }
        }
        StringBuilder sbd = new StringBuilder();
        if (minNum > -1) {
            if (i == targetStr.length()) {
                i--;
                sbd.append(targetStr.substring(0, i));
                sbd.append(digital[minNum - 1]);
            } else {
                sbd.append(targetStr.substring(0, i));
                sbd.append(digital[minNum]);
                for (int j = i + 1; j < lenT; j++) {
                    sbd.append(digital[lenD - 1]);
                }
            }
        } else {
            for (int j = 0; j < lenT - 1; j++) {
                sbd.append(digital[lenD - 1]);
            }
        }
        System.out.println(sbd.toString());
    }

    private static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] <= target) {
                left = mid;
            }
        }
        return nums[left] <= target ? left : -1;
    }
}
