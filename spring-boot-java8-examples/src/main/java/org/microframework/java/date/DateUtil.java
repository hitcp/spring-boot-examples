package org.microframework.java.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Shaoyu Liu
 * @date 2022/1/25 10:45
 **/
public class DateUtil {

    private static final String PATTERN_yyyyMMdd = "yyyy-MM-dd";

    static FastDateFormat FORMAT_yyyyMMdd = FastDateFormat.getInstance(PATTERN_yyyyMMdd);

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return FORMAT_yyyyMMdd.format(date);
    }

    public static Date parseYyyyMMdd(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return FORMAT_yyyyMMdd.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    private DateUtil() {
    }

}
