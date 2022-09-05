package org.microframework.java.date.annotation;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * fastjson 支持：入参转换，返回数据格式化
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-05
 */
public class JSONFieldAnnotation implements Serializable {

    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @JSONField(format = "yyyy-MM-dd")
    private LocalDate localDate;

    @JSONField(format = "HH:mm:ss")
    private LocalTime localTime;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
