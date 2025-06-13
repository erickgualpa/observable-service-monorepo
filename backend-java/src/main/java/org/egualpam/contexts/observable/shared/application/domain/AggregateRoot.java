package org.egualpam.contexts.observable.shared.application.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class AggregateRoot {

    private final Set<DomainEvent> domainEvents = new HashSet<>();

    protected void add(DomainEvent event) {
        domainEvents.add(event);
    }

    public Set<DomainEvent> pullDomainEvents() {
        var pulled = Set.copyOf(domainEvents);
        domainEvents.clear();
        return pulled;
    }

    public abstract AggregateId id();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AggregateRoot that = (AggregateRoot) o;
        return id().equals(that.id());
    }

    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
