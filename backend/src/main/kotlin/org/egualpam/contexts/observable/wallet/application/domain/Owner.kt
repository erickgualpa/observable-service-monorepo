package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEntity

class Owner private constructor(
  private val id: OwnerId,
  private val username: OwnerUsername
) : DomainEntity() {
  companion object {
    fun create(
      id: String,
      username: String
    ) = Owner(
        OwnerId(id),
        OwnerUsername(username),
    )
  }

  override fun getId() = id

  fun getUsername() = this.username
}
