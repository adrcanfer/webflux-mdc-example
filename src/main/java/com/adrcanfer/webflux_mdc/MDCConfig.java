package com.adrcanfer.webflux_mdc;


import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ContextSnapshotFactory;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Hooks;

import java.util.Arrays;

@Configuration
@ConditionalOnClass({ContextRegistry.class, ContextSnapshotFactory.class})
public class MDCConfig {

    public MDCConfig() {
        Arrays.asList("traceID", "name")
                .forEach(claim -> ContextRegistry.getInstance()
                        .registerThreadLocalAccessor(claim,
                                () -> MDC.get(claim),
                                value -> MDC.put(claim, value),
                                () -> MDC.remove(claim)));

        Hooks.enableAutomaticContextPropagation();
    }
}
