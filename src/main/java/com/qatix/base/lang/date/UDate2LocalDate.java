package com.qatix.base.lang.date;

import java.time.*;
import java.util.Date;

public class UDate2LocalDate {
    public static void main(String[] args) {

        //Date -> LocalDateTime
        Date date = new Date();
        System.out.println(date);

        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        //Date -> LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);

        //Date -> LocalTime
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        //LocalDateTime -> Date
        LocalDateTime localDateTime1 = LocalDateTime.now();
        Instant instant1 = localDateTime1.atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant1);
        System.out.println(date1);

        //LocalDate -> Date
        LocalDate localDate1 = LocalDate.now();
        Instant instant2 = localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date2 = Date.from(instant2);
        System.out.println(date2);

        //LocalTime -> Date
        LocalDate localDate2 = LocalDate.now();
        LocalTime localTime2 = LocalTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate2, localTime2);
        Instant instant3 = localDateTime2.atZone(ZoneId.systemDefault()).toInstant();
        Date date3 = Date.from(instant3);
        System.out.println(date3);

        //sql Date <-> util Date
        System.out.println("sql Date <-> util Date:");
        java.util.Date date4 = new java.util.Date(new java.sql.Date(System.currentTimeMillis()).getTime());
        System.out.println(date4);
        java.sql.Date date5 = new java.sql.Date(new java.util.Date().getTime());
        System.out.println(date5);
    }
}
