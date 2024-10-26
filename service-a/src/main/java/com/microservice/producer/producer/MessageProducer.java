package com.microservice.producer.producer;

import org.springframework.cloud.stream.function.StreamBridge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
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
