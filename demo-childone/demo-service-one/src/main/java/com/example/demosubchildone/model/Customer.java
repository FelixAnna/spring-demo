package com.example.demosubchildone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @NotEmpty
    @JsonProperty("id")
    private String id;

    @NotEmpty
    @JsonProperty("userName")
    private String name;
}
