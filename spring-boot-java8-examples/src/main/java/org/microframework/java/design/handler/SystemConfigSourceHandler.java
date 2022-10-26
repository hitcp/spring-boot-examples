package org.microframework.java.design.handler;

import org.microframework.java.design.ConfigSourceHandler;

/**
 * @author Shaoyu Liu
 * @date 2022-09-27
 */
public class SystemConfigSourceHandler implements ConfigSourceHandler {
    @Override
    public String get(String key) {
        System.out.println("SystemConfigSourceHandler.get");
        return "SystemConfigSourceHandler.get";
    }
}
