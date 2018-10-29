package com.qatix.base.log.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 4:50 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private int age;
    private int status;
    private String grade;
    private String sex;
}
