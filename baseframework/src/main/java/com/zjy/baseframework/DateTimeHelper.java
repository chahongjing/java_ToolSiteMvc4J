package com.zjy.baseframework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/20.
 */
public class DateTimeHelper {
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date dateTimeToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        Date date = new Date();
        LocalDate localDate = dateToLocalDate(date);
        LocalDateTime localDateTime = dateToLocalDateTime(date);

        Date date1 = dateTimeToDate(localDate);
        Date date2 = localDateTimeToDate(localDateTime);
        System.out.println(date);
        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(date1);
        System.out.println(date2);
    }
}
