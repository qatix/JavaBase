package com.qatix.base.lang.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("zhangsan",1000);
        Employee e2 = new Employee("lisi",500);
        Employee e3 = new Employee("wangwu",300);
        Employee e4 = new Employee("zhangliu",4000);
        Employee[] employees = new Employee[]{e1,e2,e3,e4};
        Arrays.sort(employees);
        System.out.println(Arrays.toString(employees));


        Arrays.sort(employees,new EmployeeComparator());
        System.out.println(Arrays.toString(employees));

        Arrays.sort(employees, (o1, o2) -> {
            if(o1.getSalary() > o2.getSalary()){
                return 1;
            }else{
                return -1;
            }
        });
        System.out.println(Arrays.toString(employees));
    }

}
