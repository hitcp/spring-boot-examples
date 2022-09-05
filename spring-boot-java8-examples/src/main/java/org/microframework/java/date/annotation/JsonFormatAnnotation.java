package org.microframework.java.date.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * jackson 支持：入参转换，返回数据格式化
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-05
 */
public class JsonFormatAnnotation implements Serializable {
    /**
     * 默认GMT时间，中国时间需要设置GMT+8
     * GMT 就是格林威治标准时间的英文缩写(Greenwich Mean Time 格林尼治标准时间)，是世界标准时间，gmt+8 是格林威治时间+8小时，中国所在时区就是gmt+8 。
     */

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime localDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate localDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
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
