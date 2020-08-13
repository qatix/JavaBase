package com.qatix.base.lang.array;

public class TwoDArray {
    public static void main(String[] args) {
        //初始化二维数组
        int[][] arr = new int[5][6];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
            System.out.println("");
        }
    }
}
