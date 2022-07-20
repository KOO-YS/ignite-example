package com.yaans.example;

import com.yaans.example.config.IgniteCacheComponent;
import com.yaans.example.model.Product;
import org.jboss.resteasy.reactive.RestPath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ignite")
public class IgniteResource {

    @GET
    @Path("{key}")
    public void cache(@RestPath String key) {
        IgniteCacheComponent<Product> productCache = new IgniteCacheComponent<>();

        productCache.put(key, Product.builder().id(1).name("water").isOnSale(true).build());

        productCache.get(key);

    }
}