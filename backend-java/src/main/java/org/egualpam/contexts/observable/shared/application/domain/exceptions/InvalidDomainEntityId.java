package org.egualpam.contexts.observable.shared.application.domain.exceptions;

public class InvalidDomainEntityId extends RuntimeException {
    public InvalidDomainEntityId(String value) {
        super("The provided id [%s] is invalid".formatted(value));
    }
}
