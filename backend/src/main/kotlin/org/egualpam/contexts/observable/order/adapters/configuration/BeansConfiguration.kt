package org.egualpam.contexts.observable.order.adapters.configuration

import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfiguration {

  @Bean
  fun createOrder() = CreateOrder()

  @Bean
  fun findOrder() = FindOrder()
}
