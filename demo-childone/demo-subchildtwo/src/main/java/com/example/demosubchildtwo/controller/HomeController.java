package com.example.demosubchildtwo.controller;

import com.example.demosubchildone.model.Customer;
import com.example.demosubchildone.service.AdmService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class HomeController {

    private WebClient.Builder webClient;
    private final AdmService admService;

    public HomeController(AdmService admService, @LoadBalanced WebClient.Builder wcBuilder) {
        this.admService = admService;
        this.webClient = wcBuilder;
    }

    @GetMapping("/mono")
    public Mono<String> getMonoValue(){
        Mono<String> result = webClient.build().get().uri("http://two-app/home/running").retrieve().bodyToMono(String.class);
        return result;
    }

    @GetMapping("/customers/all")
    public Flux<Customer> getFluxArrayValue(){
        Flux<Customer> customers =admService.getCustomers();

        return customers;
    }

    @GetMapping("/customers/{id}")
    public Mono<Customer> getFluxValue(@PathVariable("id")String id){
        Mono<Customer> customer = admService.getCustomerById(id);

        return customer;
    }

    @PostMapping(value = "/customers")
    public Mono<Customer> saveCustomer(@Valid @RequestBody Customer customer){
        customer.setName(customer.getName()+"new name");
        return Mono.just(customer);
    }

    @PostMapping(value = "")
    public Mono<String> saveCustomer(){
        return Mono.just("hi");
    }
}
