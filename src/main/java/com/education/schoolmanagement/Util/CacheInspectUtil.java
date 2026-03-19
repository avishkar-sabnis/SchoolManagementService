package com.education.schoolmanagement.Util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class CacheInspectUtil {

    private final CacheManager cacheManager;

    public CacheInspectUtil(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    /*public void inspectAllCaches() {
        Cache cache = cacheManager.getCache("Students");
        ConcurrentMapCache nativeCache = (ConcurrentMapCache) cache;

        System.out.println(nativeCache.getNativeCache());
    }*/

    public void inspectAllCaches() {
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);

            System.out.println("Cache: " + cacheName);

            if (cache instanceof ConcurrentMapCache) {
                Map<Object, Object> map =
                        ((ConcurrentMapCache) cache).getNativeCache();

                map.forEach((k, v) -> {
                    System.out.println("Main Key = " + k);

                    if (v instanceof Map<?, ?> innerMap) {

                        innerMap.forEach((innerKey, innerValue) -> {
                            System.out.println("  Inner Key=" + innerKey +
                                    ", Value=" + innerValue);
                        });
                    } else {
                        System.out.println("  Value=" + v);
                    }
                });

            } else {
                System.out.println("  Cannot inspect this cache type");
            }
        }
    }

}
