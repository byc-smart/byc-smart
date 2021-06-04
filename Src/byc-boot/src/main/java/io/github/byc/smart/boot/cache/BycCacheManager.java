package io.github.byc.smart.boot.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : bamboo
 */
@Component
public class BycCacheManager {
    @Cacheable({"abc"})
    public List<String> getRolesByResource(String resource) {
        List<String> list = new ArrayList<>();
        if ("1".endsWith(resource)) {
            list.add("中文1");
        } else if ("2".endsWith(resource)) {
            list.add("中文2");
        } else {
            list.add("others");
        }
        return list;
    }

    @CacheEvict({"abc"})
    public void evictRolesByResource(String resource) {}

    @Cacheable(cacheManager = "redisCacheManager", cacheNames = {"abcd"})
    public List<String> getRolesByResource2(String resource) {
        List<String> list = new ArrayList<>();
        if ("1".endsWith(resource)) {
            list.add("Map中文1");
        } else if ("2".endsWith(resource)) {
            list.add("Map中文2");
        } else {
            list.add("others");
        }
        return list;
    }
}
