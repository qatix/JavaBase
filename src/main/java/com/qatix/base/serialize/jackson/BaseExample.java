package com.qatix.base.serialize.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Sets;
import lombok.Data;

public class BaseExample {

    @Data
    static class Car {
        private String brand;
        private long price;
        private int load;
        private long id;
    }

    @Data
    static class House {
        private String title;
        private long price;
        private long id;
    }

    @Data
    static class Person {
        @JsonFilter("car")
        private Car car;
        @JsonIgnore
        private House house;
        private long weight;
        private long id;
        private String name;
    }

    public static Person makePerson() {
        Person person = new Person();
        person.setName("加多");
        person.setWeight(150);
        person.setId(8888);

        House house = new House();
        house.setId(234);
        house.setTitle("杭州院子");
        house.setPrice(10000000);
        person.setHouse(house);

        Car car = new Car();
        car.setBrand("玛莎拉蒂");
        car.setId(123L);
        car.setLoad(3);
        car.setPrice(1000000);
        person.setCar(car);

        return person;
    }

    public static void main(String[] arg) throws JsonProcessingException {
        Person person = makePerson();

        //2.序列化为json字符串并输出
        ObjectMapper objectMapper = new ObjectMapper();
        //一旦一个类上加了@JsonFilter注解，如果没有指定过滤器，则运行时会报错
//        System.out.println(objectMapper.writeValueAsString(person));

        //2.1创建过滤器
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        //2.1.1Person类的属性过滤器（只序列化car,house,name字段）
        filterProvider.addFilter("person", SimpleBeanPropertyFilter.filterOutAllExcept(Sets.newHashSet("car", "house", "name")));
        //2.1.2House类的属性过滤器（只序列化title,price字段）
        filterProvider.addFilter("house", SimpleBeanPropertyFilter.filterOutAllExcept(Sets.newHashSet("title", "price")));
        //2.1.3Car类的属性过滤器（只序列化brand字段）
        filterProvider.addFilter("car", SimpleBeanPropertyFilter.filterOutAllExcept(Sets.newHashSet("brand")));

        //2.2设置过滤器,并执行序列化
        objectMapper.setFilterProvider(filterProvider);
        System.out.println(objectMapper.writeValueAsString(person));
    }
}
