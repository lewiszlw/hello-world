package com.github.lewiszlw.prometheusdemo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ApiController {

    @Autowired
    MeterRegistry registry;

    private Counter helloCounter;
    private AtomicInteger onlineGauge;

    @PostConstruct
    private void init(){
        helloCounter = registry.counter("app_requests_method_count", "method", "ApiController.hello");
        onlineGauge = registry.gauge("app_online_gauge", new AtomicInteger(0));
    }

    @GetMapping(value = "/hello")
    public Object hello() {
        helloCounter.increment();
        return helloCounter.count() + " hello of ApiController";
    }

    @GetMapping(value = "/online")
    public Object online(@RequestParam Integer gauge) {
        onlineGauge.set(gauge);
        return onlineGauge.get() + " persons online now";
    }
}
