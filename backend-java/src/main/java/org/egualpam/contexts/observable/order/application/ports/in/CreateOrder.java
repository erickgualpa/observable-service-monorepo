package org.egualpam.contexts.observable.order.application.ports.in;

import org.egualpam.contexts.observable.order.application.domain.Order;
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository;
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus;

public class CreateOrder {

    private final OrderRepository repository;
    private final EventBus eventBus;

    public CreateOrder(OrderRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void execute(CreateOrderCommand command) {
        var order = Order.create(command.orderId());
        repository.save(order);
        eventBus.publish(order.pullDomainEvents());
    }
}