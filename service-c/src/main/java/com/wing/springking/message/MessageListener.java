package com.wing.springking.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageListener {

    private final ThreadPoolTaskExecutor taskExecutor;

    public Consumer<String> message() {
        return message -> taskExecutor.execute(
                () -> {
                    try {
                        log.info("Kafka Message Received: {} your are on service C", message);
                    } catch (Exception e) {
                        log.error("Kafka Received message unexpected error", e);
                    }
                });
    }
}
