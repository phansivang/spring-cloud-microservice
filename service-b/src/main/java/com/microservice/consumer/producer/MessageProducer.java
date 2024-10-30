package com.microservice.consumer.producer;

import brave.Span;
import brave.Tracer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

  private final StreamBridge streamBridge;

  private final RestTemplate restTemplate;

  public void message() {
    try {
      restTemplate.postForObject("http://localhost:8003/service-c", "", String.class);
      log.info("Kafka Message Sent");
    } catch (Exception e) {
      log.error("Kafka Failed To Send Message : {}", e.getMessage());
    }
  }
}
