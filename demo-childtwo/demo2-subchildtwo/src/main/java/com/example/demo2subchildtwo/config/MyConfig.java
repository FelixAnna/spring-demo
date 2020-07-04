package com.example.demo2subchildtwo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
@Data
public class MyConfig {

    //@NotEmpty
    private String hi;
}
