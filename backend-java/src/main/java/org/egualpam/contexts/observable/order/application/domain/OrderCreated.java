package org.egualpam.contexts.observable.order.application.domain;

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent;
import org.egualpam.contexts.observable.shared.application.domain.DomainEventId;

import java.time.Instant;

public class OrderCreated extends DomainEvent {

    private final DomainEventId id;
    private final Instant occurredOn;

    private OrderCreated(DomainEventId id, Instant occurredOn, Order order) {
        super(order);
        this.id = id;
        this.occurredOn = occurredOn;
    }

    public static OrderCreated create(String id, Instant occurredOn, Order order) {
        return new OrderCreated(new DomainEventId(id), occurredOn, order);
    }

    @Override
    public DomainEventId id() {
        return id;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}