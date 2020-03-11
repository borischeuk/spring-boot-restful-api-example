package com.example.restful_api.config.properties;

import lombok.Data;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.springframework.util.backoff.ExponentialBackOff.*;

@Data
public class RetryProperties {

    public int retryMaxAttempts = 3;

    public long backOffInitialInterval = DEFAULT_INITIAL_INTERVAL;

    public double backOffMultiplier = DEFAULT_MULTIPLIER;

    public long backOffMaxInterval = DEFAULT_MAX_INTERVAL;

    public Duration connectTimeout = ofSeconds(60);

    public Duration readTimeout = ofSeconds(30);

}
