package com.example.demosubchildone.service;

import com.example.demosubchildone.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdmServiceImpl implements AdmService {
    @Override
    public Mono<Customer> getCustomerById(String id) {
        return Mono.just(Customer.builder().id(id).name("any").build());
    }

    @Override
    public Flux<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().id("1").name("one").build());
        customers.add(Customer.builder().id("2").name("two").build());
        customers.add(Customer.builder().id("3").name("three").build());
        return Flux.fromIterable(customers);
    }
}
