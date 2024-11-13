package com.adrcanfer.webflux_mdc.controllers;

import com.adrcanfer.webflux_mdc.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @GetMapping("hello")
    public Mono<String> greetings(@RequestParam String name) {
        MDC.put("traceID", UUID.randomUUID().toString());
        MDC.put("name", name);

        LOGGER.info("INI greetings");
        Mono<String> res = testService.sayHello(name).doOnSuccess((x) -> LOGGER.info("END greetings"));

        return res;
    }

}
