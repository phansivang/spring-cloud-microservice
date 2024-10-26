package com.microservice.consumer.consumer;

import com.microservice.consumer.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageListener {

    private final MessageProducer messageProducer;

    @Bean
    public Consumer<String> message() {
        return news -> {
            log.info("Received News! \"{}\"'", news);
            messageProducer.message();

        };
    }
}
