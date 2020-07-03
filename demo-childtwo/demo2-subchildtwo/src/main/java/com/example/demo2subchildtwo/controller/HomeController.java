package com.example.demo2subchildtwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/running")
    public Mono<String> getValue(){
        return Mono.just("hello world demo 2!");
    }
}
