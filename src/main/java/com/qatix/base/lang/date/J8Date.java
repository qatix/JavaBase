package com.qatix.base.lang.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class J8Date {
    public static void main(String[] args) {
        //instant
        System.out.println("Instant");
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(new Date().getTime());
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println("-----------");

        //LocalDateTime
        System.out.println("LocalDateTime");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String st = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                ZoneId.of("Asia/Shanghai")));
        System.out.println(st);

        System.out.println(LocalDateTime.now());
        System.out.println(DateTimeFormatter.ofPattern("YYYY-MM-dd").format(LocalDate.now()));
        System.out.println("-----------");

        //ZonedDateTime
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());

        //temporalField and temporlaAdjusters
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.plusDays(1));
        System.out.println(localDateTime.with(ChronoField.YEAR, 2013));
        System.out.println(localDateTime.with(ChronoField.DAY_OF_YEAR, 20));
        System.out.println(localDateTime.with(ChronoField.MONTH_OF_YEAR, 12));
        System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(localDateTime.get(ChronoField.YEAR));
        System.out.println(localDateTime.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(localDateTime.get(ChronoField.DAY_OF_YEAR));
        System.out.println(localDateTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(localDateTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println(localDateTime.minusDays(1));
        System.out.println(localDateTime.minusMinutes(5));
        System.out.println("-----------");

        //date to string
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                ZoneId.of("Asia/Shanghai"))));
    }
}
