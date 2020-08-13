package com.qatix.base.algorithm.sort;

public class MergeSort {

    private int s[];

    public MergeSort(int[] s) {
        this.s = s;
    }

    public static void main(String[] args) {
        int arr[] = {15, 2, 79, 44, 3, 6, 7, 65, 888, 1121, 4, 31, 88, 60};
        MergeSort ms = new MergeSort(arr);
        ms.print();
        ms.sort(0, arr.length - 1);
        ms.print();
    }

    public void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int la[] = new int[n1 + 1];
        int ra[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            la[i] = s[left + i];
        }

        for (int j = 0; j < n2; j++) {
            ra[j] = s[mid + j + 1];
        }

        la[n1] = Integer.MAX_VALUE;
        ra[n2] = Integer.MAX_VALUE;

        for (int i = 0, j = 0, k = left; k <= right; k++) {
            System.out.println(i + " " + j + " " + la[i] + " " + ra[j]);
            if (la[i] < ra[j]) {
                s[k] = la[i];
                i++;
            } else {
                s[k] = ra[j];
                j++;
            }
        }
    }

    public void sort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(left, mid);
        sort(mid + 1, right);
        merge(left, mid, right);
    }

    private void print() {
        for (int i : s) {
            System.out.printf("%d ", i);
        }
        System.out.println("\n");
    }
}
