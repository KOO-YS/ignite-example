package com.yaans.example.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

@Slf4j
@Getter
public class IgniteClientComponent<T> {

    private final String CACHE_NAME = "EXAMPLE_CLIENT";
    private final IgniteClient igniteClient;
    private final ClientCache<String, T> clientCache;

    public IgniteClientComponent() {
        log.info("initialize {}", this.getClass().getName());

        ClientConfiguration config = new ClientConfiguration().setAddresses("127.0.0.1:10800");
        this.igniteClient = Ignition.startClient(config);

        this.clientCache = igniteClient.getOrCreateCache(CACHE_NAME);
    }

    public void put(String key, T t) {
        log.info("put data {}, \n key : {}, \tvalue : {}", t.getClass().getSimpleName(), key, t);
        clientCache.put(key, t);
    }

    public Object get(String key) {
        log.info("get data by key : {}", key);
        return clientCache.withKeepBinary().get(key);
    }
}
