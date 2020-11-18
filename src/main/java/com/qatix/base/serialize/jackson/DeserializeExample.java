package com.qatix.base.serialize.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

public class DeserializeExample {

    @Data
    static class Car {
        private String brand;
        private long price;
        private int load;
        private long id;
    }


    public static void main(String[] arg) throws JsonProcessingException {
        Car car = new Car();
        car.setBrand("Lanbom");
        car.setId(123L);
        car.setLoad(3);
        car.setPrice(1000000);

        //2.序列化为json字符串并输出
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(car);
        System.out.println(jsonStr);
        //pretty print
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car));


        Car car2 = objectMapper.readValue(jsonStr, Car.class);
        System.out.println(car2.toString());

        //全局启用 pretty print
        ObjectMapper mapper2 = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper2.writeValueAsString(car2));
    }
}
