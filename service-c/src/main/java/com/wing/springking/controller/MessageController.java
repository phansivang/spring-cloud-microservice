package com.wing.springking.controller;

import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("service-c")
public class MessageController {
    private final Tracer tracer;
    @PostMapping
    public void sendMessage(@Header) {
        Span span = tracer.currentSpan();
        span.tag("service", "message");

        System.out.println("ARRIVED!");
    }

}
