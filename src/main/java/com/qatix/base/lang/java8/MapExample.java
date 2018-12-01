package com.qatix.base.lang.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:58 AM
 */
public class MapExample {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        for (int i=0;i<10;i++){
            map.putIfAbsent(i,"val"+i);
        }

        System.out.println(map);

        map.computeIfPresent(3,(num,val)->val+num);
        System.out.println(map.get(3));

        map.computeIfPresent(9,(num,val)->null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.containsKey(23));

        map.computeIfAbsent(3,num->"bam");
        System.out.println(map.get(3));

        //remove by key and val
        map.remove(3,"val3");
        System.out.println(map.get(3));

        map.remove(3,"val33");
        System.out.println(map.get(3));

        System.out.println(map.get(9));
        map.merge(9,"val9",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(9));

        map.merge(9,"concat",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(9));

        System.out.println("forEach:");
        map.forEach((key,val)-> System.out.println(key+":"+val));
    }
}
