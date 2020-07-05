package com.example.demosubchildtwo.controller;

import com.example.demosubchildone.model.Customer;
import com.example.demosubchildone.service.AdmService;
import com.example.demosubchildtwo.config.MyConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final MyConfig myConfig;
    private WebClient.Builder webClient;
    private final AdmService admService;

    public HomeController(AdmService admService, @LoadBalanced WebClient.Builder wcBuilder, MyConfig myConfig) {
        this.admService = admService;
        this.webClient = wcBuilder;
        this.myConfig = myConfig;
    }

    @GetMapping("/index")
    public Mono<String> getIndex(){
        String hiOne = myConfig.getHi();
        Mono<String> result = webClient.build().get().uri("http://sub2childtwo/home/index")
                .retrieve()
                .bodyToMono(String.class);

         return Mono.zip(result, Mono.just(hiOne), (a, b)->String.format("%s %s", a, b));
    }

    @GetMapping("/customers/all")
    public Flux<Customer> getAllCustomers(){
        Flux<Customer> customers =admService.getCustomers();

        return customers;
    }

    @GetMapping("/customers/{id}")
    public Mono<Customer> getCustomerById(@PathVariable("id")String id){
        Mono<Customer> customer = admService.getCustomerById(id);

        return customer;
    }

    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON)
    public Mono<Customer> saveCustomer(@Valid @RequestBody Mono<Customer> customer){
        customer = customer.map(x->{x.setName(x.getName()+"new name"); return x;});
        return customer;
    }
}
