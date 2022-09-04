package com.yaans.example;

import com.yaans.example.config.client.IgniteClientComponent;
import com.yaans.example.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestPath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Slf4j
@Path("client")
@RequiredArgsConstructor
public class ClientResource {

    @GET
    @Path("/{key}")
    public void connect(@RestPath String key) {
        IgniteClientComponent<Product> clientCache = new IgniteClientComponent<>();

        clientCache.put(key, Product.builder().id(3).name("coffee").isOnSale(true).build());

        Object value = clientCache.get(key);
        log.info("get value : {}", value.toString());
    }

}