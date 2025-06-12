package org.egualpam.contexts.observable.shared.adapters.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class ErrorMetrics {

    private final MeterRegistry meterRegistry;

    public ErrorMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void error(Exception cause) {
        Counter.builder("application_error_occurred")
                .tag("type", cause.getClass().getSimpleName())
                .tag("app", "order-service-backend-java")
                .register(meterRegistry)
                .increment();
    }
}
