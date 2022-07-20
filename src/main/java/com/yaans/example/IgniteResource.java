package com.yaans.example;

import com.yaans.example.config.IgniteCacheComponent;
import com.yaans.example.config.IgniteComponent;
import com.yaans.example.config.IgniteJDBCComponent;
import com.yaans.example.model.Product;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestPath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("ignite")
@RequiredArgsConstructor
public class IgniteResource {

    private final IgniteComponent igniteComponent = new IgniteComponent();

    @GET
    @Path("cache/{key}")
    public void cache(@RestPath String key) {
        IgniteCacheComponent<Product> productCache = new IgniteCacheComponent<>(igniteComponent);

        productCache.put(key, Product.builder().id(1).name("water").isOnSale(true).build());

        productCache.get(key);

    }

    @GET
    @Path("jdbc")
    public void jdbc() throws SQLException, ClassNotFoundException {
        IgniteJDBCComponent jdbcCache = new IgniteJDBCComponent(igniteComponent);

        int returnId = jdbcCache.put(Product.builder().id(2).name("ice").isOnSale(false).build());

        jdbcCache.get(returnId);
    }

}