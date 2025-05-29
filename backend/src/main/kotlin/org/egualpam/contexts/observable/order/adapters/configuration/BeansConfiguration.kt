package org.egualpam.contexts.observable.order.adapters.configuration

import org.egualpam.contexts.observable.order.adapters.out.MySqlOrderRepository
import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrder
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient

@Configuration
class BeansConfiguration {

  @Bean
  fun createOrder(repository: OrderRepository) = CreateOrder(repository)

  @Bean
  fun findOrder(repository: OrderRepository) = FindOrder(repository)

  @Bean
  fun orderRepository(jdbcClient: JdbcClient) = MySqlOrderRepository(jdbcClient)
}
