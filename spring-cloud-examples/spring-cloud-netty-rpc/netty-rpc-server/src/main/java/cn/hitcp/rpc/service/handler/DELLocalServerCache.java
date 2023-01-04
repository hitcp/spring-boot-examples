package cn.hitcp.rpc.service.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class DELLocalServerCache {
    private static final Map<String, Object> serverCacheMap = new HashMap<>();

    public static void store(String serverName, Object server) {
        serverCacheMap.merge(serverName, server, (Object oldObj, Object newObj) -> newObj);
    }

    public static Object get(String serverName) {
        return serverCacheMap.get(serverName);
    }

    public static Map<String, Object> getAll(){
        return null;
    }
}
