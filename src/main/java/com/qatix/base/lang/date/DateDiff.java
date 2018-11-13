package com.qatix.base.lang.date;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/8 8:00 PM
 */
public class DateDiff {
    public static void main(String[] args) throws ParseException {
        String params = "20180102-20180201";
        String[] parts = params.split("-");
        Date dateStart = DateUtils.parseDate(parts[0], "YYYYMMdd");
        Date dateEnd = DateUtils.parseDate(parts[1], "YYYYMMdd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(dateStart);
        end.setTime(dateEnd);
        while (!start.after(end)) {
            System.out.println(start.getTime());
            start.add(Calendar.DATE, 1);
        }
    }
}
