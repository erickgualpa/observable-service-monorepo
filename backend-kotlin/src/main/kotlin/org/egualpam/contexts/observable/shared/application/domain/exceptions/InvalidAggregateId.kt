package org.egualpam.contexts.observable.shared.application.domain.exceptions

class InvalidAggregateId(value: String) :
  RuntimeException("The provided id [$value] is invalid")
