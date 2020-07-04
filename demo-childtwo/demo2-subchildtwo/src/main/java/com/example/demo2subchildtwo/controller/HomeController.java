package com.example.demo2subchildtwo.controller;

import com.example.demo2subchildtwo.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final MyConfig myConfig;

    public HomeController(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @GetMapping("/running")
    public Mono<String> getValue(){
        return Mono.just(myConfig.getHi());
    }
}
