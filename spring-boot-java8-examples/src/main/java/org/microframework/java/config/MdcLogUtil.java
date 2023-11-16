package org.microframework.java.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * Created by god on 2018/12/28.
 */
@Slf4j
public abstract class MdcLogUtil {

    public static String getLogIdBydefault() {
        return MDC.get("ruid");
    }

    public static String getLogId() {
        String logId = MDC.get("ruid");
        if (StringUtils.isEmpty(logId)) {
            return setLogId();
        } else {
            return logId;
        }
    }

    public static void resetLogId() {
        cleanLogId();
        setLogId();
    }

    private static String setLogId() {
        String ip = "_0";
        try {
            for (String str : NetworkUtil.getNetworkIPList()) {
                if (!"127.0.0.1".equals(str)) {
                    ip = "_" + str.substring(str.lastIndexOf(".") + 1, str.length());
                }
            }
        } catch (Exception e) {
            log.error("setLogId error", e);
        }
        String logId = ShortUUID.uuid() + ip;
        MDC.put("ruid", logId);
        return logId;
    }

    public static void cleanLogId() {
        MDC.remove("ruid");
    }

}
