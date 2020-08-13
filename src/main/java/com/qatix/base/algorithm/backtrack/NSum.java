package com.qatix.base.algorithm.backtrack;

import java.util.*;

public class NSum {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, -1, -2, -3, -5, 6};
        data = new int[]{-1,0,1,2,-1,-4};
//        List<Integer>  ls = new ArrayList<>();
//        ls.sort(Comparator.comparingInt(Integer::intValue));
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        Set<String> rset = new HashSet<>();
        int len = data.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                for(int k=j+1;k<len;k++){
                    if(data[i]+data[j]+data[k] == 0){
                        String uKey = String.format("%d-%d-%d",data[i],data[j],data[k]);
                        if(rset.contains(uKey)){
                            continue;
                        }
                        rset.add(uKey);
                        System.out.printf("%d + %d + %d = 0\n",data[i],data[j],data[k]);
                    }
                }
            }
        }
    }
}
