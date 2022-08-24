package org.microframework.java.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shaoyu Liu
 * @date 2022-08-08
 */
public class ThreadLocalExample {

    /**
     * 解决SimpleDateFormat并发安全问题
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final Map<String, String> map = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        System.out.println(DATE_FORMAT.get().format(new Date()));
    }
}

