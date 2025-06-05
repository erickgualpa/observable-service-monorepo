package org.egualpam.contexts.observable.shared.application.domain;

import org.egualpam.contexts.observable.shared.application.domain.exceptions.InvalidAggregateId;

import java.util.UUID;

public record AggregateId(String value) {
    public AggregateId {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidAggregateId(value);
        }
    }
}
