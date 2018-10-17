package com.qatix.base.algorithm.sort;

public class QuickSort {

    private int s[];

    public QuickSort(int[] s) {
        this.s = s;
    }

    private void swap(int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }


    public void sort(int left, int right) {
        if (left >= right) {
            return;
        }

        int x = s[left];
        int i = left;
        int j = right;
        System.out.println(x);
        while (i < j) {
            while (i < j && s[j] >= x) {
                j--;
            }

            while (i < j && s[i] <= x) {
                i++;
            }

            if (i < j) {
                swap(i, j);
                print();
            }
        }

        System.out.println( i  + "=" + s[i]);
        s[left] = s[i];
        s[i] = x;
        this.sort(left, i - 1);
        this.sort(i + 1, right);
    }

    private void print() {
        for (int i : s) {
            System.out.printf("%d ", i);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int arr[] = {1,1,2,15, 2, 79,44, 3, 6, 7, 65, 888, 1121, 4, 31, 88, 60};
        QuickSort qs = new QuickSort(arr);
        qs.print();
        qs.sort(0, arr.length - 1);
        qs.print();
    }
}
