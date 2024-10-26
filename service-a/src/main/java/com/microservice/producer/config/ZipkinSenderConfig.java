package com.microservice.producer.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.kafka.KafkaSender;

import java.util.Map;

@Configuration
public class ZipkinSenderConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${management.zipkin.kafka.topic}")
    private String zipkinTopic;

    @Value("${spring.cloud.stream.kafka.binder.brokers}")
    private String kafkaBootstrapServer;


    @Bean("zipkinSender")
    public KafkaSender senderZipkin() {
        Map<String, Object> kafkaProducerProperties = buildKafkaProducerProperties();
        return KafkaSender.newBuilder().topic(zipkinTopic).overrides(kafkaProducerProperties).build();
    }

    private Map<String, Object> buildKafkaProducerProperties() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName(),
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName(),
                CommonClientConfigs.CLIENT_ID_CONFIG, applicationName
        );
    }
}