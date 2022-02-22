package com.example.demoapp;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class Person {
    @NonNull
    private String name;
    @NonNull
    private List<Child> children;
}
