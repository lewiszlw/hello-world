package com.github.lewiszlw.elkdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class ApiController {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void init() {
        executorService.scheduleAtFixedRate(() -> {
            all();
        }, 1, 2, TimeUnit.SECONDS);
    }

    @GetMapping("/all")
    public void all() {
        log.trace(UUID.randomUUID().toString());
        log.debug(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
        log.warn(UUID.randomUUID().toString());
        log.error(UUID.randomUUID().toString());
    }

    @GetMapping("/trace")
    public void trace() {
        log.trace(UUID.randomUUID().toString());
    }

    @GetMapping("/debug")
    public void debug() {
        log.debug(UUID.randomUUID().toString());
    }

    @GetMapping("/info")
    public void info() {
        log.info(UUID.randomUUID().toString());
    }

    @GetMapping("/warn")
    public void warn() {
        log.warn(UUID.randomUUID().toString());
    }

    @GetMapping("/loggingerror")
    public void error() {
        log.error(UUID.randomUUID().toString());
    }
}
