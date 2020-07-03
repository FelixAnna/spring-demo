package com.example.demosubchildone.service;

import com.example.demosubchildone.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdmService {
    Mono<Customer> getCustomerById(String id);

    Flux<Customer> getCustomers();
}
