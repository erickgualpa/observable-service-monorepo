package org.egualpam.contexts.observable.shared.application.ports.out;

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent;

import java.util.Set;

public interface EventBus {
    void publish(Set<DomainEvent> events);
}
