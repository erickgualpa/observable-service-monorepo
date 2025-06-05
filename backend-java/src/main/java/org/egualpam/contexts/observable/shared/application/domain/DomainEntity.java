package org.egualpam.contexts.observable.shared.application.domain;

public abstract class DomainEntity {

    abstract DomainEntityId id();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity that = (DomainEntity) o;
        return id().equals(that.id());
    }

    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
