package com.example.demoadminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class DemoAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAdminServerApplication.class, args);
    }

}
