package org.egualpam.contexts.observable.order.application.ports.out;

import org.egualpam.contexts.observable.order.application.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> find(String orderId);

    void save(Order order);
}
