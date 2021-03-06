package com.qatix.base.algorithm.sort;

public class HeapSort {

    private int s[];

    public HeapSort(int[] s) {
        this.s = s;
    }

    public static void main(String[] args) {
        int arr[] = {15, 2, 79, 44, 3, 6, 7, 65, 888, 1121, 4, 31, 88, 60};
        HeapSort hs = new HeapSort(arr);
        hs.print();
        hs.sort(arr.length);
        hs.print();
    }

    private void swap(int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public void maxheapfixdown(int i, int n) {
        int j = 2 * i + 1;
        int temp = s[i];
        while (j < n) {
            if (j + 1 < n && s[j + 1] > s[j]) {
                ++j;//get max child
            }
            if (temp > s[j]) {
                break;
            } else {
                s[i] = s[j];
                i = j;
                j = 2 * i + 1;
            }
        }
        s[i] = temp;
    }

    public void sort(int n) {
        //构建大顶推
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxheapfixdown(i, n);
        }
        print();
        //每次把最大值从0位置移动到最后的位置，同时再平衡
        for (int i = n - 1; i >= 1; i--) {
            swap(i, 0);
            //再堆化
            maxheapfixdown(0, i);
        }
    }

    private void print() {
        for (int i : s) {
            System.out.printf("%d ", i);
        }
        System.out.println("\n");
    }
}
