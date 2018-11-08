package com.qatix.base.lang.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/3 8:53 PM
 */
public class HashMapExample {
    public static void main(String[] args) {
        System.out.println("HashMap");
        Map<String,String> map = new HashMap<>();
        map.put("a3","11");
        map.put("a2","22");
        map.put("b1","33");
        map.put("c1","44");
        map.put("nullvalue",null);
        map.put(null,"nullkey");
        map.put(null,null);

        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }

        System.out.println("LinkedHashMap");
        //内部维持了一个双向链表,可以保持顺序
        map = new LinkedHashMap<>();//能保留插入的顺序
        map.put("a3","11");
        map.put("a2","22");
        map.put("b1","33");
        map.put("c1","44");
        map.put("nullvalue",null);
        map.put(null,"nullkey");
        map.put(null,null);

        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }

        System.out.println("HashTable");
        map = new Hashtable<>();//能保留插入的顺序
        map.put("a3","11");
        map.put("a2","22");
        map.put("b1","33");
        map.put("c1","44");
//        map.put("nullvalue",null);  不允许，NPE
//        map.put(null,"nullkey"); 不允许，NPE
//        map.put(null,null); 不允许，NPE

        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }
    }
}
/*
* HashMap
nullvalue|null
null|null
a2|22
a3|11
c1|44
b1|33
LinkedHashMap
a3|11
a2|22
b1|33
c1|44
nullvalue|null
null|null
HashTable
a2|22
b1|33
c1|44
a3|11
*/
