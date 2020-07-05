package com.example.demo2subchildtwo.controller;

import com.example.demo2subchildtwo.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/home")
public class HomeController {
    final
    WebClient.Builder webClientBuilder;

    private final MyConfig myConfig;

    public HomeController(MyConfig myConfig, WebClient.Builder webClientBuilder) {
        this.myConfig = myConfig;
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/index")
    public Mono<String> getIndex(){
        return Mono.just(myConfig.getHi());
    }

    @GetMapping("/call/back")
    public Mono<String> getCallback(){
        Mono<String> result = webClientBuilder.build().get().uri("http://subchildtwo/home/index")
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }
}
