package com.qatix.base.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.ListMultimap;
import org.apache.curator.shaded.com.google.common.collect.MultimapBuilder;

import java.util.Map;

public class MultiMapTest {
    public static void main(String[] args) {
        // creates a ListMultimap with tree keys and array list values
        ListMultimap<String, Integer> treeListMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();

// creates a SetMultimap with hash keys and enum set values
//        SetMultimap<Integer, MyEnum> hashEnumMultimap =
//                MultimapBuilder.hashKeys().enumSetValues(MyEnum.class).build();

        Map<String, Integer> nameToId = Maps.newHashMap();
        Map<Integer, String> idToName = Maps.newHashMap();

        nameToId.put("Bob", 42);
        idToName.put(42, "Bob");

        System.out.println(nameToId.toString());
        System.out.println(idToName);


        BiMap<String, Integer> userIdMap = HashBiMap.create();
        userIdMap.put("aaa", 111);
        userIdMap.put("bbb", 332);
        String userForId = userIdMap.inverse().get(111);
        System.out.println(userForId);
    }
}
