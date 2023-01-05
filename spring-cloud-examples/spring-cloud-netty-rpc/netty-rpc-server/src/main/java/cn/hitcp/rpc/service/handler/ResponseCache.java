package cn.hitcp.rpc.service.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class ResponseCache {
    private static final Map<String, Object> requestResponseMap = new HashMap<>();

    public static void store(String serverName, Object server) {
        requestResponseMap.merge(serverName, server, (Object oldObj, Object newObj) -> newObj);
    }

    public static Object get(String serverName) {
        return requestResponseMap.get(serverName);
    }

    public static Object put(long requestId, Object data) {
        return requestResponseMap.put(String.valueOf(requestId), data);
    }

    public static Map<String, Object> getAll() {
        return requestResponseMap;
    }

    private ResponseCache() {
    }
}
