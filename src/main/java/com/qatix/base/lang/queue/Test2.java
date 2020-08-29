package com.qatix.base.lang.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.toArray;

public class Test2 {
    public static class Saver{
        private int n;
        private int[] saveList;
        public Saver(int n){
            this.n = n;
            this.saveList = new int[n];
            this.saveList[0] = 0;
            this.calc();
        }

        private void calc(){
            int start = 1;
            int step = 1;
            while(start < this.n){
                for(int i = 0;i<step && start+i < this.n;i++){
                    this.saveList[start+i] = i;
                }
                start = start + step;
                step = step * 2;
                System.out.println(Arrays.toString(this.saveList));
            }
            System.out.println(Arrays.toString(this.saveList));
        }
        public int getSaver(int k,int n){
            return this.saveList[k];
        }

        public int[] getSaverList(int k){
            List<Integer> resList = new ArrayList<>();
            for(int i=0;i<this.n;i++){
                if(this.saveList[i] == k){
                    resList.add(i);
                }
            }
            return resList.stream().mapToInt(Integer::intValue).toArray();
        }

        public int getRound(int k,int n){
            return getLog(k);
        }
        private int getLog(int k){
            int t = 1;
            int i = 1;
            while (t < k ){
                t = t + 1 << i;
                i++;
            }
            return i;
        }
    }


    public static void main(String[] args) {
        Saver saver = new Saver(9);

    }
}
