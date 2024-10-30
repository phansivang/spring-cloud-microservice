package com.microservice.consumer.interceptor;

import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

public class TraceIdPropagationInterceptor implements ClientHttpRequestInterceptor {

    private final Tracer tracer;

    public TraceIdPropagationInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution
    ) throws IOException {
        TraceContext traceContext = Objects.requireNonNull(tracer.currentSpan()).context();

        request.getHeaders().add("X-B3-TraceId", traceContext.traceId());
        request.getHeaders().add("X-B3-SpanId", traceContext.spanId());
        request.getHeaders().add("X-B3-Sampled", Boolean.TRUE.equals(traceContext.sampled()) ? "1" : "0");

        return execution.execute(request, body);
    }
}
