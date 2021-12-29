package com.github.lewiszlw.arthasdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AlphaService {

    @Autowired
    private BetaService betaService;

    public List<String> queryNamesByAge(int age) throws InterruptedException {
        List<BetaService.Person> persons = betaService.queryPersonsByAge(age);
        Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        if (CollectionUtils.isEmpty(persons)) {
            return Collections.emptyList();
        }
        return persons.stream().map(BetaService.Person::name).collect(Collectors.toList());
    }
}
