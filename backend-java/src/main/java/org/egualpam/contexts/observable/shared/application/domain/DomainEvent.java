package org.egualpam.contexts.observable.shared.application.domain;

import java.time.Instant;

public abstract class DomainEvent {

    private final AggregateRoot aggregateRoot;

    public DomainEvent(AggregateRoot aggregateRoot) {
        this.aggregateRoot = aggregateRoot;
    }

    public abstract DomainEventId id();

    protected AggregateId aggregateId() {
        return aggregateRoot.id();
    }

    protected abstract Instant occurredOn();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DomainEvent that = (DomainEvent) o;
        return id().equals(that.id());
    }

    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
