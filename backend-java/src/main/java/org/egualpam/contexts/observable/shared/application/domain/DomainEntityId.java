package org.egualpam.contexts.observable.shared.application.domain;

import org.egualpam.contexts.observable.shared.application.domain.exceptions.InvalidDomainEntityId;

import java.util.UUID;

public record DomainEntityId(String value) {
    public DomainEntityId {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidDomainEntityId(value);
        }
    }
}
