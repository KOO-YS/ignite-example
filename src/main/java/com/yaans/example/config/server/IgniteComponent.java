package com.yaans.example.config.server;


import com.yaans.example.model.Product;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DeploymentMode;
import org.apache.ignite.configuration.IgniteConfiguration;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@Getter
public class IgniteComponent {

    public Ignite ignite;

    public IgniteComponent() {
        IgniteConfiguration config = new IgniteConfiguration();

        // Cache stores and POJO classes cannot be peer loaded.
//        config.setPeerClassLoadingEnabled(true);
//        config.setDeploymentMode(DeploymentMode.CONTINUOUS);

        ignite = Ignition.start(config);

    }


    public class IgniteCacheComponent<T> {

        public IgniteCache<String, T> cache;

        public IgniteCacheComponent() {
            log.info("initialize {}", this.getClass().getName());
            cache = ignite.getOrCreateCache("EXAMPLE_CACHE");
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



}
