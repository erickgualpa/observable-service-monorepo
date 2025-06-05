package org.egualpam.contexts.observable.shared.adapters.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent;

public class DomainEventMetrics {

    private final MeterRegistry meterRegistry;

    public DomainEventMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void published(DomainEvent event) {
        Counter.builder("application_domain_events_published")
                .tag("type", event.getClass().getSimpleName())
                .register(meterRegistry)
                .increment();
    }
}
