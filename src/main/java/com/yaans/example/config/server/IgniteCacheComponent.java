package com.yaans.example.config.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.IgniteCache;


@Slf4j
public class IgniteCacheComponent <T> {

    public IgniteCache<String, T> cache;

    public IgniteCacheComponent(IgniteComponent server) {
        log.info("initialize {}", this.getClass().getName());
        cache = server.getIgnite().getOrCreateCache("EXAMPLE_CACHE");
    }

    public void put(String key, T t) {
        log.info("put data {}, \n key : {}, \tvalue : {}", t.getClass().getSimpleName(), key, t);
        cache.put(key, t);
    }

    public Object get(String key) {
        log.info("get data by key : {}", key);
        return cache.withKeepBinary().get(key);
    }
}
