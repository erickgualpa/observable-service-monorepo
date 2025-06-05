package org.egualpam.contexts.observable.order.adapters.in;

import org.egualpam.contexts.observable.order.application.ports.in.CreateOrder;
import org.egualpam.contexts.observable.order.application.ports.in.CreateOrderCommand;
import org.egualpam.contexts.observable.shared.adapters.metrics.ErrorMetrics;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/v1/orders")
public class RestCreateOrder {

    private static final Logger logger = getLogger(RestCreateOrder.class);

    private final CreateOrder createOrder;
    private final ErrorMetrics errorMetrics;
    private final TransactionTemplate transactionTemplate;

    public RestCreateOrder(
            CreateOrder createOrder,
            ErrorMetrics errorMetrics,
            TransactionTemplate transactionTemplate
    ) {
        this.createOrder = createOrder;
        this.errorMetrics = errorMetrics;
        this.transactionTemplate = transactionTemplate;
    }

    @PutMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        var command = new CreateOrderCommand(request.order().id());

        try {
            transactionTemplate.executeWithoutResult(status -> createOrder.execute(command));
            return noContent().build();
        } catch (Exception e) {
            logger.error("Unexpected error processing request [{}]:", request, e);
            errorMetrics.error(e);
            return internalServerError().build();
        }
    }

    public record CreateOrderRequest(Order order) {
        public record Order(String id) {
        }
    }
}