package org.microframework.algorithm.array;

/**
 * @author Shaoyu Liu
 * @date 2022/1/12 19:59
 */
public class T3 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 1, 0, 3, 12};
        int[] nums2 = new int[]{0, 1, 0, 3, 12};
        int[] result;
        if (nums1.length > nums2.length) {
            result = new int[nums1.length];
        } else {
            result = new int[nums2.length];
        }
        int index = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    result[index++] = nums1[i];
                }
            }
        }

        System.out.println(result);

    }
}


