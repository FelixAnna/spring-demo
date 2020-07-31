package com.example.demosubchildone.service;

import com.example.demosubchildone.model.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class AdmServiceTest {
    private AdmServiceImpl admService;

    @BeforeEach
    void initial() {
        admService = new AdmServiceImpl();
    }

    @Test
    void testGetCustomerById() {
        Mono<Customer> result = admService.getCustomerById("1");

        Assert.assertNotNull(result.block());
        Assert.assertEquals(result.block().getId(), "1");
        Assert.assertEquals(result.block().getName(), "any");
    }

    @Test
    void testGetCustomersd() {
        Flux<Customer> results = admService.getCustomers();

        Assert.assertNotNull(results);
        Assert.assertTrue(results.count().block() == 3);
        Assert.assertEquals(results.blockFirst().getId(), "1");
        Assert.assertEquals(results.last().block().getId(), "3");
    }
}
