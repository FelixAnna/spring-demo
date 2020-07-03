package com.example.demosubchildtwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.example.*"})
public class DemoSubchildtwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSubchildtwoApplication.class, args);
    }

}
