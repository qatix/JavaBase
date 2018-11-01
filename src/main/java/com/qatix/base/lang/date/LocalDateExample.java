package com.qatix.base.lang.date;

import java.sql.Timestamp;
import java.time.*;

public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(2015,3,22);
        System.out.println(ld);

        LocalTime lt  = LocalTime.of(22,23,24);
        System.out.println(lt);

        LocalDateTime ldt = LocalDateTime.of(2017,1,3,4,5,6,230);
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(2016,2,3,4,30,22),
                ZoneId.of("+08"));
        System.out.println(zdt);

        //格林威治时间，0时区
        System.out.println(Instant.now());

        //获取北京时间
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));

        System.out.println("Timestamp:");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts);
        System.out.println(ts.toInstant());
        System.out.println(ts.toLocalDateTime());
    }
}
/*
* 2015-03-22
22:23:24
2017-01-03T04:05:06.000000230
2016-02-03T04:30:22+08:00
2018-11-01T15:13:49.583Z
2018-11-01T23:13:49.651+08:00
Timestamp:
2018-11-01 23:13:49.653
2018-11-01T15:13:49.653Z
2018-11-01T23:13:49.653
*/
