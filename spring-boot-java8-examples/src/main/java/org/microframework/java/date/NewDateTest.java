package org.microframework.java.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author Shaoyu Liu
 * @date 2022/3/27 21:56
 */
public class NewDateTest {
    public static void main(String[] args) {

        LocalDate departMonth = LocalDate.parse("2023-10",DateTimeFormatter.ofPattern("yyyy-MM"));
        // 获取当前月的第一天
        LocalDate firstDay = departMonth.with(TemporalAdjusters.firstDayOfMonth());
        // 获取当前月的最后一天
        LocalDate lastDay = departMonth.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("================");

        // LocalDateTime 转 String
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        // String 转 LocalDateTime
        LocalDateTime.parse("2023-06-06 15:28:39", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        // LocalDateTime 转 Date
        Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        // LocalDate 转 Date
        Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        // Date 转 LocalDateTime
        LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());


        LocalDate localDate = LocalDate.of(2022,02,02);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        int year = localDate.get(ChronoField.YEAR);
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.get(ChronoField.DAY_OF_MONTH);

        LocalTime localTime = LocalTime.of(20,20);
        LocalTime localTime1 = LocalTime.parse("18:18:18");



        LocalDateTime localDateTime = LocalDateTime.parse("2022-04-01T18:18:18");
        LocalDateTime with = localDateTime.with(new NextWorkingDay());
        String format = with.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);//2022-03-28
        System.out.println(format);

        Instant instant;
        Duration duration;
        Period period;


    }
}
