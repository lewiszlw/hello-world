package com.github.lewiszlw.prometheusdemo;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ApiController {

    @Autowired
    MeterRegistry registry;

    private Counter helloCounter;
    private AtomicInteger onlineGauge;
    private Timer helloTimer;
    private DistributionSummary helloSummary;

    @PostConstruct
    private void init(){
        helloCounter = registry.counter("prometheus_demo_requests_method_count", "method", "ApiController.hello");
        onlineGauge = registry.gauge("prometheus_demo_online_gauge", new AtomicInteger(0));
        helloTimer = registry.timer("prometheus_demo_api_timer", "method", "ApiController.hello");
        helloSummary = registry.summary("prometheus_demo_api_request_size", "method", "ApiController.hello");

        // 模拟上报数据
        // 无法计算用户振铃率、应答率等等
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            List<Tag> tags = new ArrayList<>();
            tags.add(Tag.of("session_id", UUID.randomUUID().toString()));
            tags.add(Tag.of("hangup-cause", random("normal-clearing", "no-answer", "busy")));
            tags.add(Tag.of("hangup-by", random("robot", "", "busy")));
            tags.add(Tag.of("ringing", random("true", "false")));
            tags.add(Tag.of("answer", random("true", "false")));
            tags.add(Tag.of("duration", String.valueOf(new Random().nextInt(100))));
            registry.summary("prometheus_demo_call", tags).record(1);
        }, 1, 1, TimeUnit.SECONDS);
    }

    @GetMapping(value = "/hello")
    public Object hello() {
        // 统计接口调用次数
        helloCounter.increment();

        // 统计接口响应耗时
        helloTimer.record(() -> {
            // 模拟业务逻辑耗时
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 统计接口请求大小
        helloSummary.record(new Random().nextInt(100));  // 使用随机数代表request size

        return helloCounter.count() + " hello of ApiController";
    }

    @GetMapping(value = "/online")
    public Object online(@RequestParam Integer gauge) {
        // 统计实时在线人数
        onlineGauge.set(gauge);
        return onlineGauge.get() + " persons online now";
    }

    private String random(String... elements) {
        return elements[new Random().nextInt(elements.length)];
    }
}
