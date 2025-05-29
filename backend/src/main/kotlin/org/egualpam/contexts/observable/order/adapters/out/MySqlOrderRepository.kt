package org.egualpam.contexts.observable.order.adapters.out

import org.egualpam.contexts.observable.order.application.domain.Order
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository
import org.springframework.jdbc.core.simple.JdbcClient
import java.time.Instant

class MySqlOrderRepository(
  private val jdbcClient: JdbcClient
) : OrderRepository {
  override fun find(orderId: String): Order? {
    val result = jdbcClient.sql("SELECT * FROM order_entity WHERE id = :id")
        .param("id", orderId)
        .query(OrderDto::class.java)
        .optional()
    return result.map { Order.load(it.id) }.orElse(null)
  }

  override fun save(order: Order) {
    jdbcClient.sql("INSERT IGNORE INTO order_entity (id, created_at) VALUES (:id, :createdAt)")
        .param("id", order.getId().value())
        .param("createdAt", Instant.now())
        .update()
  }

  data class OrderDto(val id: String)
}
