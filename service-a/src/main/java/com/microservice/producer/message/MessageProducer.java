package com.microservice.producer.message;

import org.springframework.cloud.stream.function.StreamBridge;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

  private final StreamBridge streamBridge;

  public void message() {
    try {
      this.streamBridge.send("message-out-0", "Hello Microservice !");
      log.info("Kafka Message Sent");
    } catch (Exception e) {
      log.error("Kafka Failed To Send Message : {}", e.getMessage());
    }
  }
}
