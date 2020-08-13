package com.qatix.base.algorithm.backtrack;

import java.util.Arrays;

public class NQueue {
    private int size;
    private int[] data;
    private int resultCount;

    public NQueue(int size) {
        this.size = size;
        this.data = new int[size + 1];
        this.resultCount = 0;
    }

    private boolean checkOk(int n) {
        for (int i = 1; i <= n - 1; i++) {
            if (this.data[i] == this.data[n] || Math.abs(this.data[i] - this.data[n]) == n - i) {
                return false;
            }
        }
        return true;
    }

    private void output() {
        for (int i = 1; i <= this.size; i++) {
            System.out.printf("%d ", this.data[i]);
        }
        System.out.print("\n");
    }

    private void backTrack(int k) {
        if (k > this.size) {
            this.output();
            this.resultCount++;
        } else {
            for (int i = 1; i <= this.size; i++) {
                this.data[k] = i;
                if (checkOk(k)) {
                    backTrack(k + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        NQueue nQueue = new NQueue(8);
        nQueue.output();
        nQueue.backTrack(1);
        System.out.println("total:" + nQueue.resultCount);
    }
}
