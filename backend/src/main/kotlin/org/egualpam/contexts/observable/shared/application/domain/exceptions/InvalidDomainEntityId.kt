package org.egualpam.contexts.observable.shared.application.domain.exceptions

class InvalidDomainEntityId(value: String) :
  RuntimeException("The provided id [$value] is invalid")
