package com.qatix.base.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/14 7:22 PM
 */
public class BSearch {

    /**
     * 返回有重复结果的最后一个索引
     *
     * @param s
     * @param k
     * @return
     */
    private static int bearch(int s[], int k) {
        if (null == s) {
            System.err.println("Array should not be null");
            return -1;
        }
        if (s.length < 1) {
            return -1;
        }
        if (s.length == 1 && s[0] == k) {
            return 0;
        }
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] > k) {
                right = mid - 1;
            } else if (s[mid] < k) {
                left = mid + 1;
            } else {
                if (s[mid + 1] > k) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 返回有重复结果的第一个索引
     *
     * @param s
     * @param k
     * @return
     */
    private static int bearch2(int s[], int k) {
        if (null == s) {
            System.err.println("Array should not be null");
            return -1;
        }
        if (s.length < 1) {
            return -1;
        }
        if (s.length == 1 && s[0] == k) {
            return 0;
        }

        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] > k) {
                right = mid - 1;
            } else if (s[mid] < k) {
                left = mid + 1;
            } else {
                if (mid - 1 < 0 || s[mid - 1] < k) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 返回找到的第一个结果，不检查重复
     *
     * @param s
     * @param k
     * @return
     */
    private static int bearch3(int s[], int k) {
        if (null == s) {
            System.err.println("Array should not be null");
            return -1;
        }
        if (s.length < 1) {
            return -1;
        }
        if (s.length == 1 && s[0] == k) {
            return 0;
        }

        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] > k) {
                right = mid - 1;
            } else if (s[mid] < k) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] s = new int[]{1, 2, 3, 3, 3, 4, 11, 11, 12, 45};
        System.out.println(s.length);
        System.out.println(Arrays.toString(s));

        System.out.println(bearch(s, 3));
        System.out.println(bearch(s, 100));
        System.out.println(bearch(s, 11));
        System.out.println(bearch(s, 2));
        System.out.println(bearch(null, 3));

        System.out.println("bsearch2:");
        System.out.println(bearch2(s, 3));
        System.out.println(bearch2(s, 2));

        System.out.println("bsearch3:");
        System.out.println(bearch3(s, 3));

        int[] s2 = new int[]{2};
        System.out.println(bearch(s2, 2));
        System.out.println(bearch2(s2, 2));


        int[] s3 = new int[]{2, 3};
        System.out.println(bearch(s3, 2));
        System.out.println(bearch2(s3, 2));


    }
}
