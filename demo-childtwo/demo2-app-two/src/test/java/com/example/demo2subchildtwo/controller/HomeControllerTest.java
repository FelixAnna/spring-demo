package com.example.demo2subchildtwo.controller;

import com.example.demo2subchildtwo.config.MyConfig;
import com.example.demo2subchildtwo.controller.HomeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class HomeControllerTest {
    ObjectMapper objectMapper=new ObjectMapper();

    private WebTestClient client;

    @Mock
    private MyConfig myConfig;

    private WebClient.Builder webClientBuilder;

    @BeforeEach
    void init(){
        webClientBuilder = WebClient.builder()
                .exchangeFunction(clientRequest -> Mono.just(ClientResponse.create(HttpStatus.OK)
                        .header("content-type", "application/json")
                        .body("hi there")
                        .build())
                );
        client=WebTestClient.bindToController(new HomeController(myConfig, webClientBuilder)).build();
        Mockito.when(myConfig.getHi()).thenReturn("hi");
    }

    @Test
    void testIndex() {
        client.get().uri("/home/index").exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$").isEqualTo("hi");
    }

    @Test
    void testCallback() {
        client.get().uri("/home/call/back").exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$").isEqualTo("hi there");
    }
}
