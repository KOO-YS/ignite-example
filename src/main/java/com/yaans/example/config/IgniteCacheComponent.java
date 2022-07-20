package com.yaans.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.IgniteCache;

@Slf4j
public class IgniteCacheComponent<T> extends IgniteComponent{

    IgniteCache<String, T> cache;

    public IgniteCacheComponent() {
        super();
        log.info("initialize {}", this.getClass().getName());
        cache = ignite.getOrCreateCache("EXAMPLE_CACHE");
    }

    public void put(String key, T t) {
        log.info("put data {}, \n key : {}, \tvalue : {}", t.getClass().getName(), key, t);
        cache.put(key, t);
    }

    public T get(String key) {
        log.info("get data by key : {}", key);
        return cache.get(key);
    }
}
