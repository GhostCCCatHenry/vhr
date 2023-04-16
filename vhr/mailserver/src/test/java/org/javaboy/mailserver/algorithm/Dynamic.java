package org.javaboy.mailserver.algorithm;

public class Dynamic {
    public static void main(String[] args) {
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(a));
    }

    private static int trap(int[] height) {
        if (height.length == 1 || height.length == 2) return 0;
        int res = 0;
        int tmpRight = 0;
        int tmpIndex = 0;
        for (int i = 0, j = 1;
             i < height.length - 1; j=i+1) {
            if (height[i] == 0) {
                i++;
                continue;
            }
            for (;j < height.length; j++) {
                if (height[j] >= tmpRight) {
                    tmpRight = height[j];
                    tmpIndex = j;
                }
                if (tmpRight > height[i]) {
                    break;
                }
            }
            if (tmpIndex - i >= 2) {
                res += Math.min(height[i], tmpRight) * (tmpIndex-i-1);
                for (int k = i+1; k<tmpIndex; k++) {
                    res -= height[k];
                }
            }
            tmpRight = 0;
            i = tmpIndex;
        }
        return res;
    }
}
