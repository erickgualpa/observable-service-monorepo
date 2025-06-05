package org.egualpam.contexts.observable.order.application.ports.in;

import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository;

public class FindOrder {

    private final OrderRepository repository;

    public FindOrder(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderDto execute(FindOrderQuery query) {
        var orderId = query.orderId();
        var order = repository.find(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderDto(order.id().value());
    }
}