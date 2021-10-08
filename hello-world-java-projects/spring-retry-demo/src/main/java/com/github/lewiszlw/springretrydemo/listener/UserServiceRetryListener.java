package com.github.lewiszlw.springretrydemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class UserServiceRetryListener implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {

    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.error("retry error {}", new Date(), throwable);
    }
}
