package org.egualpam.contexts.observable.order.application.domain;

import org.egualpam.contexts.observable.shared.application.domain.AggregateId;
import org.egualpam.contexts.observable.shared.application.domain.AggregateRoot;

import java.time.Instant;

import static java.util.UUID.randomUUID;

public class Order extends AggregateRoot {

    private final AggregateId id;

    private Order(AggregateId id) {
        this.id = id;
    }

    @Override
    public AggregateId id() {
        return id;
    }

    public static Order create(String id) {
        var order = new Order(new AggregateId(id));

        var event = OrderCreated.create(
                randomUUID().toString(),
                Instant.now(),
                order
        );

        order.add(event);
        return order;
    }

    public static Order load(String id) {
        return new Order(new AggregateId(id));
    }
}