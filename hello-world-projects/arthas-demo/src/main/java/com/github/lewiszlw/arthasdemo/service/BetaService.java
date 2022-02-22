package com.github.lewiszlw.arthasdemo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class BetaService {

    private static List<Person> personData = new ArrayList<>();
    static {
        personData.add(new Person("Tom", 18));
        personData.add(new Person("John", 19));
        personData.add(new Person("Alice", 20));
        personData.add(new Person("Bob", 18));
        personData.add(new Person("Tim", 17));
    }

    public List<Person> queryPersonsByAge(int age) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        return personData.stream().filter(person -> person.age == age).collect(Collectors.toList());
    }

    public record Person(String name, int age) { }
}
