package com.microservice.producer.core.aspect;

import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TracingAspect {

    private final Tracer tracer;

    @AfterThrowing(throwing = "exception")
    public void addTagAfterThrowingException(Exception exception) {
        trace().tag("error", exception.getMessage());
    }

    private Span trace(){
        return tracer.currentSpan();
    }
}