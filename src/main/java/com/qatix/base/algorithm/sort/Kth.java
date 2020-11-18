package com.qatix.base.algorithm.sort;

public class Kth {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;//注意这里的k已经变了
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int i = lo;
            //这里把数组以A[lo]的大小分为两部分，
            //一部分是小于A[lo]的，一部分是大于A[lo]的
            // [lo,i]<A[lo]，[i+1,j)>=A[lo]
            for (int j = lo + 1; j <= hi; j++) {
                if (nums[j] < nums[lo]) {
                    swap(nums, j, ++i);
                }
            }
            swap(nums, lo, i);
            if (k == i) {
                return nums[i];
            } else if (k < i) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return -1;
    }


    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }


    public int findKthLargestRecursive(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int lo, int hi, int k) {
        int pivot = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] <= nums[hi]) {
                swap(nums, pivot++, j);
            }
        }
        swap(nums, pivot, hi);
        int count = hi - pivot + 1;
        // 如果找到直接返回
        if (count == k) {
            return nums[pivot];
        }
        // 从右边部分找
        if (count > k) {
            return quickSelect(nums, pivot + 1, hi, k);
        }
        // 从左边部分找
        return quickSelect(nums, lo, pivot - 1, k - count);
    }
}
