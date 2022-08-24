package org.microframework.java.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutionException;


/**
 * @author Shaoyu Liu
 * @date 2021/11/5 10:14
 **/
class CacheGuavaTest {
//    private static LoadingCache<Object, Object> cache = CacheBuilder.newBuilder()
//            .maximumSize(10)//最多存放十个数据
//            .expireAfterWrite(10, TimeUnit.SECONDS)//缓存10秒，10秒之后进行回收

    //                .refreshAfterWrite(10, TimeUnit.SECONDS)
//            .recordStats()//开启，记录状态数据功能
//            .build(
//                    new CacheLoader<Object, Object>() {
//                        //数据加载，默认返回-1，也可以是查询操作，如从DB查询
//                        @Override
//                        public Integer load(Object key) throws Exception {
//                            // TODO Auto-generated method stub
//                            log.info("load search db.....");
//                            return null;
//                        }
//
//                    });
    private static final Cache<Object, Integer> CACHE = CacheBuilder.newBuilder().maximumSize(10).build();


    public static void main(String[] args) {

        Integer id = null;
        if (StringUtils.isEmpty(id) || id == 0) {
            System.out.println("1111111");
        }
        for (int i = 0; i < 200; i++) {
            CACHE.put("key" + i, i);
        }
        Object key190 = CACHE.getIfPresent("key190");
        Object key191 = CACHE.getIfPresent("key191");
        Object key192 = CACHE.getIfPresent("key192");
        Object key193 = CACHE.getIfPresent("key193");
        Object key194 = CACHE.getIfPresent("key194");
        Object key195 = CACHE.getIfPresent("key195");
        Object key196 = CACHE.getIfPresent("key196");
        Object key197 = CACHE.getIfPresent("key197");
        Object key198 = CACHE.getIfPresent("key198");
        Object key199 = CACHE.getIfPresent("key199");
        Object key1902 = CACHE.getIfPresent("key190");
        CACHE.put("asfdaf", 5455);
        CACHE.getIfPresent("key100");
//        log.info(String.valueOf(cache.size()));
        Object key99 = CACHE.getIfPresent("key99");
        try {
            CACHE.get("key12190", () -> {
//                log.error("search database...");

                return null;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Object o = CACHE.get("key12190", () -> {
//                log.info("2search database...");
                return null;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

//    @Getter
//    @Setter
    private static class User {
        private Integer id;
    }
}
