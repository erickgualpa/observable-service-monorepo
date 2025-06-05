package org.egualpam.contexts.observable.order.adapters.in;

import org.egualpam.contexts.observable.order.application.ports.in.FindOrder;
import org.egualpam.contexts.observable.order.application.ports.in.FindOrderQuery;
import org.egualpam.contexts.observable.order.application.ports.in.OrderDto;
import org.egualpam.contexts.observable.shared.adapters.metrics.ErrorMetrics;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/orders")
public class RestFindOrder {

    private static final Logger logger = getLogger(RestFindOrder.class);

    private final FindOrder findOrder;
    private final ErrorMetrics errorMetrics;

    public RestFindOrder(FindOrder findOrder, ErrorMetrics errorMetrics) {
        this.findOrder = findOrder;
        this.errorMetrics = errorMetrics;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable String orderId) {
        var query = new FindOrderQuery(orderId);

        try {
            var order = findOrder.execute(query);
            var response = OrderResponse.from(order);
            return ok(response);
        } catch (Exception e) {
            logger.error("Unexpected error processing order with id [{}]", orderId, e);
            errorMetrics.error(e);
            return internalServerError().build();
        }
    }

    public record OrderResponse(Order order) {
        public static OrderResponse from(OrderDto dto) {
            return new OrderResponse(new Order(dto.id()));
        }

        public record Order(String id) {
        }
    }
}