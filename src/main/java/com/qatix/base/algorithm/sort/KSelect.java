package com.qatix.base.algorithm.sort;

public class KSelect {

    private int s[];

    public KSelect(int[] s) {
        this.s = s;
    }

    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 15, 2, 79, 44, 3, 6, 7, 65, 888, 1121, 4, 31, 88, 60};
        KSelect qs = new KSelect(arr);
        qs.print();
        int r = qs.find(5, 0, arr.length - 1);
        System.out.println(r);
    }

    private void swap(int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public int find(int k, int left, int right) {
        System.out.println("k=" + k + ",left=" + left + ",right=" + right);
        if (left >= right) {
            return -10;
        }

        int x = s[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && s[j] >= x) {
                j--;
            }

            while (i < j && s[i] <= x) {
                i++;
            }

            if (i < j) {
                swap(i, j);
            }
        }

        System.out.println(i + "=" + s[i]);
//        System.exit(1);
        s[left] = s[i];
        s[i] = x;

        if (i - left + 1 == k) {
            return s[i];
        }

        print();
        if (i - left > k) {
            return find(k, left, i - 1);
        } else {
            return find(k - (i - left) - 1, i + 1, right);
        }
    }

    private void print() {
        for (int i : s) {
            System.out.printf("%d ", i);
        }
        System.out.println("\n");
    }
}
