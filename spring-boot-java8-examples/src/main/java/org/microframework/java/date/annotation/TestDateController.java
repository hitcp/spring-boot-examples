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

    @PostMapping("testDateTimeFormatAnnotation")
    public DateTimeFormatAnnotation testDateTimeFormatAnnotation(@RequestBody DateTimeFormatAnnotation request) {
        System.out.println(request.getDate());
        System.out.println(request.getDateTime());
        DateTimeFormatAnnotation response = new DateTimeFormatAnnotation();
        response.setDate(new Date());
        response.setDateTime(new Date());
        return response;
    }

    @PostMapping("testJsonFormatAnnotation")
    public JsonFormatAnnotation testJsonFormatAnnotation(@RequestBody JsonFormatAnnotation request) {
        System.out.println(request.getDate());
        System.out.println(request.getDateTime());
        // FIXME 入参格式化日期中间带T
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
