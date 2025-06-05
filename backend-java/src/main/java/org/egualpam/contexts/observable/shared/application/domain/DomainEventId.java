package org.egualpam.contexts.observable.shared.application.domain;

import org.egualpam.contexts.observable.shared.application.domain.exceptions.InvalidDomainEntityId;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public record DomainEventId(String value) {
    public DomainEventId {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidDomainEntityId(value);
        }
    }

    public static DomainEventId generate() {
        return new DomainEventId(randomUUID().toString());
    }
}
