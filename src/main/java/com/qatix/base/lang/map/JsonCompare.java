package com.qatix.base.lang.map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonCompare {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
        //language=JSON
        String jsonElementOne = "{\n" +
                "  \"name\": \"zhangsan\",\n" +
                "  \"age\": 33\n" +
                "}";
        String jsonElementTwo = "{\n" +
                "  \"name\": \"zhangsan\",\n" +
                "  \"age\": 34\n" +
                "}";

        Map<String, Object> firstMap = gson .fromJson(jsonElementOne, mapType);
        Map<String, Object> secondMap = gson .fromJson(jsonElementTwo, mapType);
        MapDifference<String, Object> difference = Maps.difference(firstMap, secondMap);
        System.out.println(difference);
        boolean compareResult=  difference.areEqual();
        System.out.println("result:"+compareResult);
    }
}
