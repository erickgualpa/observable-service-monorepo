package org.egualpam.contexts.observable.shared.adapters.out;

import org.egualpam.contexts.observable.shared.adapters.metrics.DomainEventMetrics;
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent;
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus;
import org.slf4j.Logger;

import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class FakeEventBus implements EventBus {

    private final DomainEventMetrics domainEventMetrics;
    private final Logger logger = getLogger(this.getClass());

    public FakeEventBus(DomainEventMetrics domainEventMetrics) {
        this.domainEventMetrics = domainEventMetrics;
    }

    @Override
    public void publish(Set<DomainEvent> domainEvents) {
        domainEvents.forEach(value ->
                {
                    domainEventMetrics.published(value);
                    logger.info(
                            "Fake publishing of event [{}] with id [{}]",
                            value.getClass().getSimpleName(),
                            value.id().value()
                    );
                }
        );
    }
}
