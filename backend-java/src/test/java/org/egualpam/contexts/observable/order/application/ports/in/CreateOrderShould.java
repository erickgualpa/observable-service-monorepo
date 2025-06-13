package org.egualpam.contexts.observable.order.application.ports.in;

import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository;
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent;
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateOrderShould {

    @Captor
    private ArgumentCaptor<Set<DomainEvent>> domainEventsCaptor;

    @Mock
    private OrderRepository repository;

    @Mock
    private EventBus eventBus;

    private CreateOrder testee;

    @BeforeEach
    void setUp() {
        testee = new CreateOrder(repository, eventBus);
    }

    @Test
    void publishDomainEvents() {
        var orderId = randomUUID().toString();
        var command = new CreateOrderCommand(orderId);

        testee.execute(command);

        verify(eventBus).publish(domainEventsCaptor.capture());

        var published = domainEventsCaptor.getValue();
        assertThat(published).hasSize(1);
    }
}