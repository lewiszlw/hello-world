package com.example.demoapp;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Child {

    @NonNull
    private Integer age;
    @NonNull
    private String name;
}
