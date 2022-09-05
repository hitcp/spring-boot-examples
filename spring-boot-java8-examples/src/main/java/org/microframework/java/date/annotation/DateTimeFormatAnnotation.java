package org.microframework.java.date.annotation;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * spring框架注解 支持：入参转换
 * 可以把前端的字符串类型转为Date类型，入参如： "date":"2022-09-05 11:46:00"可直接转为对应java.util.Date类型
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-05
 * @website http://hitcp.cn
 */
public class DateTimeFormatAnnotation implements Serializable {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
