package com.wing.springking.config;

import com.wing.springking.interceptor.TraceIdPropagationInterceptor;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(Tracer tracer) {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = new TraceIdPropagationInterceptor(tracer);
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}