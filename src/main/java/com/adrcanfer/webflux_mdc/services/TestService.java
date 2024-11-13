package com.adrcanfer.webflux_mdc.services;

import com.adrcanfer.webflux_mdc.controllers.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    public Mono<String> sayHello(String name) {
        LOGGER.info("INI sayHello");
        return Mono.just("Hello " + name)
                .delayElement(Duration.ofSeconds(5))
                .doOnSuccess((x) -> LOGGER.info("END sayHello"));
    }
}
