package com.microservice.producer.controller;

import com.microservice.producer.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageProducer messageProducer;

    @PostMapping
    public void create() {
        messageProducer.message();
    }

}
