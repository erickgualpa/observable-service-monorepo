package org.egualpam.contexts.observable.order.adapters.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.egualpam.contexts.observable.order.adapters.out.MySqlOrderRepository;
import org.egualpam.contexts.observable.order.application.ports.in.CreateOrder;
import org.egualpam.contexts.observable.order.application.ports.in.FindOrder;
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository;
import org.egualpam.contexts.observable.shared.adapters.metrics.DomainEventMetrics;
import org.egualpam.contexts.observable.shared.adapters.metrics.ErrorMetrics;
import org.egualpam.contexts.observable.shared.adapters.out.FakeEventBus;
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class BeansConfiguration {

    @Bean
    public CreateOrder createOrder(OrderRepository repository, EventBus eventBus) {
        return new CreateOrder(repository, eventBus);
    }

    @Bean
    public FindOrder findOrder(OrderRepository repository) {
        return new FindOrder(repository);
    }

    @Bean
    public OrderRepository orderRepository(JdbcClient jdbcClient) {
        return new MySqlOrderRepository(jdbcClient);
    }

    @Bean
    public EventBus fakeEventBus(DomainEventMetrics domainEventMetrics) {
        return new FakeEventBus(domainEventMetrics);
    }

    @Bean
    public DomainEventMetrics domainEventMetrics(MeterRegistry meterRegistry) {
        return new DomainEventMetrics(meterRegistry);
    }

    @Bean
    public ErrorMetrics errorMetrics(MeterRegistry meterRegistry) {
        return new ErrorMetrics(meterRegistry);
    }
}