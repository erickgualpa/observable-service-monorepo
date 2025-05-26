package org.egualpam.contexts.observable.architecture

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.egualpam.contexts.observable.shared.application.domain.AggregateRoot
import org.junit.jupiter.api.Test

class WalletArchitectureTest {

  private val importedClasses =
      ClassFileImporter().importPackages("org.egualpam.contexts.observable")

  @Test
  fun `wallet entity should be the wallet aggregate root`() {
    classes().that()
        .resideInAPackage("..domain..")
        .and()
        .haveNameMatching(".*Wallet")
        .should()
        .beAssignableTo(AggregateRoot::class.java)
        .check(importedClasses)
  }
}
