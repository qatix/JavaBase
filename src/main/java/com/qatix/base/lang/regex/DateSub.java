package com.qatix.base.lang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateSub {

    public int sub(String date1, String date2) {
        return getDaythOfDate(date1) - getDaythOfDate(date2);
    }

    public int getDaythOfDate(String dateStr) {
        MyDate myDate = MyDate.parseFromString(dateStr);
        assert myDate != null;
        int sumDay = 0;
        for (int i = 1; i < myDate.year; i++) {
            if (MyDate.isLeapYear(i)) {
                sumDay += 366;
            } else {
                sumDay += 365;
            }
        }
        for (int i = 1; i < myDate.month; i++) {
            sumDay += MyDate.getDaysOfMonth(myDate.year, i);
        }
        sumDay += myDate.day;
        return sumDay;
    }

    static class MyDate {
        int year;
        int month;
        int day;

        public static boolean isLeapYear(int year) {
            return (year % 4 == 0) && ((year % 100) != 0 || (year % 400) == 0);
        }

        public static int getDaysOfMonth(int year, int month) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31;
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30;
                case 2:
                    return isLeapYear(year) ? 29 : 28;
                default:
                    throw new IllegalArgumentException("error date");
            }
        }

        public static MyDate parseFromString(String dateStr) {
            String pattern = "^(\\d{4})(\\d{2})(\\d{2})$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(dateStr);
            if (m.matches()) {
                int year = Integer.parseInt(m.group(1));
                int month = Integer.parseInt(m.group(2));
                int day = Integer.parseInt(m.group(3));

                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("month should between 1-12");
                }

                if (day < 1 || day > 31) {
                    throw new IllegalArgumentException("month should between 1-31");
                }

                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) {
                        throw new IllegalArgumentException("wrong date of day");
                    }
                }
                if (month == 2) {
                    if (!isLeapYear(year) && day > 28 || isLeapYear(year) && day > 29) {
                        throw new IllegalArgumentException("wrong date of feb day");
                    }
                }

                MyDate date = new MyDate();
                date.year = year;
                date.month = month;
                date.day = day;

                return date;
            }
            return null;
        }

        @Override
        public String toString() {
            return "MyDate{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    '}';
        }
    }

    public static void main(String[] args) {
        DateSub dateSub = new DateSub();
        System.out.println(dateSub.sub("20190103", "20190102"));
        System.out.println(dateSub.sub("20200108", "20200105"));
        System.out.println(dateSub.sub("20200308", "20190105"));
        System.out.println(dateSub.sub("20000108", "20200105"));
        System.out.println(dateSub.sub("20000238", "20200105"));
        System.out.println(dateSub.sub("20110229", "20200105"));
    }
}
