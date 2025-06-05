package org.egualpam.contexts.observable.shared.application.domain.exceptions;

public class InvalidAggregateId extends RuntimeException {
    public InvalidAggregateId(String value) {
        super("The provided id [%s] is invalid".formatted(value));
    }
}
