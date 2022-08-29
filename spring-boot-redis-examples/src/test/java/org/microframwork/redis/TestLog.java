package org.microframwork.redis;

import org.junit.Test;
import org.microframework.reids.config.LogHandler;

/**
 * @author Shaoyu Liu
 * @date 2022-08-29
 */
public class TestLog extends LogHandler {

    @Test
    public void testLog(){
        log.info("use LogHandler print...");
    }
}
