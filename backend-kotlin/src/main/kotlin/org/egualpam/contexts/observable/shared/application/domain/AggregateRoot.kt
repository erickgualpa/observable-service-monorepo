package org.egualpam.contexts.observable.shared.application.domain

abstract class AggregateRoot {

  private val domainEvents = mutableSetOf<DomainEvent>()

  protected fun add(domainEvent: DomainEvent) = domainEvents.add(domainEvent)

  fun pullDomainEvents(): Set<DomainEvent> {
    val pulled = domainEvents.toSet()
    domainEvents.clear()
    return pulled
  }

  abstract fun getId(): AggregateId

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as AggregateRoot

    return getId() == other.getId()
  }

  override fun hashCode(): Int {
    return getId().hashCode()
  }
}
