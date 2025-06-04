package org.egualpam.contexts.observable.order.adapters.configuration

import io.micrometer.core.instrument.MeterRegistry
import org.egualpam.contexts.observable.order.adapters.out.MySqlOrderRepository
import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrder
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository
import org.egualpam.contexts.observable.shared.adapters.metrics.DomainEventMetrics
import org.egualpam.contexts.observable.shared.adapters.metrics.ErrorMetrics
import org.egualpam.contexts.observable.shared.adapters.out.FakeEventBus
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient

@Configuration
class BeansConfiguration {

  @Bean
  fun createOrder(
    repository: OrderRepository,
    eventBus: EventBus
  ) = CreateOrder(repository, eventBus)

  @Bean
  fun findOrder(repository: OrderRepository) = FindOrder(repository)

  @Bean
  fun orderRepository(jdbcClient: JdbcClient) = MySqlOrderRepository(jdbcClient)

  @Bean
  fun fakeEventBus(
    domainEventMetrics: DomainEventMetrics
  ): EventBus = FakeEventBus(domainEventMetrics)

  @Bean
  fun domainEventMetrics(
    meterRegistry: MeterRegistry
  ) = DomainEventMetrics(meterRegistry)

  @Bean
  fun errorMetrics(
    meterRegistry: MeterRegistry
  ) = ErrorMetrics(meterRegistry)
}
