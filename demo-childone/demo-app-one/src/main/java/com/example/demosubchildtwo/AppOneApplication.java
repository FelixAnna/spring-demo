package com.example.demosubchildtwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class AppOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppOneApplication.class, args);
    }

}
