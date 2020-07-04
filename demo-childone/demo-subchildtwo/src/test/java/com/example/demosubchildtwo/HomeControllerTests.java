package com.example.demosubchildtwo;

import com.example.demosubchildone.model.Customer;
import com.example.demosubchildone.service.AdmService;
import com.example.demosubchildtwo.config.MyConfig;
import com.example.demosubchildtwo.controller.HomeController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTests {

    ObjectMapper objectMapper=new ObjectMapper();

    private WebTestClient client;
    @Mock
    private MyConfig myConfig;

    @Mock
    private AdmService admService;

    private WebClient.Builder webClientBuilder;

    @BeforeEach
    void init(){
        webClientBuilder = WebClient.builder()
                .exchangeFunction(clientRequest -> Mono.just(ClientResponse.create(HttpStatus.OK)
                        .header("content-type", "application/json")
                        .body("hi")
                        .build())
                );
        client=WebTestClient.bindToController(new HomeController(admService, webClientBuilder, myConfig)).build();
    }

    @Test
    void testGetMono() {
        Mockito.when(myConfig.getHi()).thenReturn("there");
        client.get().uri("/home/mono").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEqualTo("hi there");

    }

    @Test
    void testGetFluxArray(){
        Flux<Customer> fakeCustomers=Flux.just(Customer.builder().id("1").name("one").build()
                ,Customer.builder().id("2").name("two").build()
                ,Customer.builder().id("3").name("three").build());
        Mockito.when(admService.getCustomers()).thenReturn(fakeCustomers);

        client.get().uri("/home/customers/all").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].userName").isEqualTo("one")
                .jsonPath("$[2].id").isEqualTo("3")
                .jsonPath("$[2].userName").isEqualTo("three");
    }

    @Test
    void testGetFluxString() throws JsonProcessingException {
        Mono<Customer> fakeCustomer=Mono.just(Customer.builder().id("1").name("any").build());
        Mockito.when(admService.getCustomerById(Mockito.any())).thenReturn(fakeCustomer);

        client.get().uri("/home/customers/1").exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(objectMapper.writeValueAsString(fakeCustomer.block()));
    }
}
