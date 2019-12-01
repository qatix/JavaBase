package com.qatix.base.common.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilExample {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        testBasic();
        testObj2Map();
        testMap2Obj();
    }

    private static void testMap2Obj() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("testMap2Obj");
        Map<String, String> personMap = new HashMap<>();
        personMap.put("name", "map2obj");
        personMap.put("age", "322");
        personMap.put("address", "beijing");


        final Person person = new Person();
        BeanUtils.populate(person, personMap);
        System.out.println(person.toString());
    }

    private static void testObj2Map() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("testObj2Map");
        final Person person = new Person();
        person.setName("zhang");
        person.setAge(100);

        Map<String, String> personMap = BeanUtils.describe(person);
        System.out.println(personMap);
    }

    private static void testBasic() throws InvocationTargetException, IllegalAccessException {
        System.out.println("testBasic");
        final BeanUtilsBean bub = new BeanUtilsBean();
        final Person person = new Person();
        person.setName("zhang");
        person.setAge(100);
        try {
            System.out.println(bub.getProperty(person, "name"));
            System.out.println(BeanUtils.getProperty(person, "age"));
//            fail("Could access name property!");
        } catch (final NoSuchMethodException ex) {
            // ok
            ex.fillInStackTrace();
        }
    }
}
