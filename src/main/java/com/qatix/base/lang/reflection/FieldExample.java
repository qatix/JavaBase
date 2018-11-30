package com.qatix.base.lang.reflection;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/23 6:43 PM
 */
public class FieldExample {
    @Data
    private static class GetParams{
        private Integer categoryId;
        private Integer page;
        private String country;
    }

    public static void main(String[] args) throws IllegalAccessException {

        GetParams getParams = new GetParams();
        getParams.setCategoryId(22);
        getParams.setPage(11);

        for(Field field:getParams.getClass().getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.getName() +":");
            System.out.println(field.get(getParams));
        }
    }

}
