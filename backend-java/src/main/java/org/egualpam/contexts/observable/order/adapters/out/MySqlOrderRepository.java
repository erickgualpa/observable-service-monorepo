package org.egualpam.contexts.observable.order.adapters.out;

import org.egualpam.contexts.observable.order.application.domain.Order;
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.Instant;
import java.util.Optional;

public class MySqlOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public MySqlOrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Order> find(String orderId) {
        var result = jdbcClient
                .sql("SELECT * FROM order_entity WHERE id = :id")
                .param("id", orderId)
                .query(OrderDto.class)
                .optional();

        return result.map(dto -> Order.load(dto.id()));
    }

    @Override
    public void save(Order order) {
        jdbcClient
                .sql("INSERT IGNORE INTO order_entity (id, created_at) VALUES (:id, :createdAt)")
                .param("id", order.id().value())
                .param("createdAt", Instant.now())
                .update();
    }

    public record OrderDto(String id) {
    }
}