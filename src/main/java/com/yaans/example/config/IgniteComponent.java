package com.yaans.example.config;


import lombok.Getter;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

@Getter
public class IgniteComponent {

    public Ignite ignite;

    public IgniteComponent() {
        ignite = Ignition.start();


    }
}
