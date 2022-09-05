package org.microframework.java.date.annotation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-05
 */
@RestController
@RequestMapping("date")
public class TestDateController {

    /**
     * 只格式化入参，对返回数据格式无效
     * 注意：请求参数不能用空格，中间要带T
     * 请求：
     * {
     * "dateTime":"2022-01-01T15:01:01",
     * "date":"2022-01-01"
     * }
     * 返回：
     * {
     * "dateTime": "2022-09-05T09:09:35.290+00:00",
     * "date": "2022-09-05T09:09:35.290+00:00"
     * }
     *
     * @param request
     * @return
     */
    @PostMapping("testDateTimeFormatAnnotation")
    public DateTimeFormatAnnotation testDateTimeFormatAnnotation(@RequestBody DateTimeFormatAnnotation request) {
        // 注意：格式化后会默认+8个小时
        // 入参："date":"2022-09-05"，格式化后：Mon Sep 05 08:00:00 CST 2022
        // 入参："dateTime":"2022-01-01T15:01:01"，格式化后：Sat Jan 01 23:01:01 CST 2022
        System.out.println(request.getDate());
        System.out.println(request.getDateTime());
        DateTimeFormatAnnotation response = new DateTimeFormatAnnotation();
        response.setDate(new Date());
        response.setDateTime(new Date());
        return response;
    }

    /**
     * 请求：
     * {
     * "dateTime":"2022-01-01 15:01:01",
     * "date":"2022-01-01",
     * "localDateTime": "2022-01-01 15:02:01",
     * "localDate": "2022-01-01",
     * "localTime": "15:01:01"
     * }
     * 返回：
     * {
     * "date": "2022-09-05",
     * "dateTime": "2022-09-05 09:02:49",
     * "localDateTime": "2022-09-05 17:02:49",
     * "localDate": "2022-09-05",
     * "localTime": "17:02:49"
     * }
     *
     * @param request
     * @return
     */
    @PostMapping("testJsonFormatAnnotation")
    public JsonFormatAnnotation testJsonFormatAnnotation(@RequestBody JsonFormatAnnotation request) {
        System.out.println(request.getDate());
        System.out.println(request.getDateTime());
        // TODO 入参格式化日期中间带T
        System.out.println(request.getLocalDateTime());
        System.out.println(request.getLocalDate());
        System.out.println(request.getLocalTime());
        JsonFormatAnnotation response = new JsonFormatAnnotation();
        response.setDate(new Date());
        response.setDateTime(new Date());
        response.setLocalDateTime(LocalDateTime.now());
        response.setLocalDate(LocalDate.now());
        response.setLocalTime(LocalTime.now());
        return response;
    }

    /**
     * 注意：请求参数不能用空格，中间要带T
     * 请求：
     * {
     * "dateTime":"2022-01-01T15:01:01",
     * "date":"2022-01-01",
     * "localDateTime": "2022-01-01T15:02:01",
     * "localDate": "2022-01-01",
     * "localTime": "15:01:01"
     * }
     * 返回（格式化无效）：
     * {
     * "date": "2022-09-05T09:04:04.088+00:00",
     * "dateTime": "2022-09-05T09:04:04.088+00:00",
     * "localDateTime": "2022-09-05T17:04:04.088",
     * "localDate": "2022-09-05",
     * "localTime": "17:04:04.088"
     * }
     *
     * @param request
     * @return
     */
    @PostMapping("testJSONFieldAnnotation")
    public JSONFieldAnnotation testJSONFieldAnnotation(@RequestBody JSONFieldAnnotation request) {
        System.out.println(request.getDate());
        System.out.println(request.getDateTime());
        System.out.println(request.getLocalDateTime());
        System.out.println(request.getLocalDate());
        System.out.println(request.getLocalTime());
        JSONFieldAnnotation response = new JSONFieldAnnotation();
        response.setDate(new Date());
        response.setDateTime(new Date());
        response.setLocalDateTime(LocalDateTime.now());
        response.setLocalDate(LocalDate.now());
        response.setLocalTime(LocalTime.now());
        return response;
    }

}
