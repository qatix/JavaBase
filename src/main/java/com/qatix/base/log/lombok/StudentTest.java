package com.qatix.base.log.lombok;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 5:03 PM
 */
@Slf4j
public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(21);
        student.setName("zhang");
        log.info(student.toString());

        log.info(JSON.toJSONString(student));

        Student st = new Student("ccc", 44, 2, "g3", "male");
        log.info(st.toString());
    }
}
