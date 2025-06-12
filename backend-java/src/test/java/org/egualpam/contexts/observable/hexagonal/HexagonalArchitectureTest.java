package org.egualpam.contexts.observable.hexagonal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class HexagonalArchitectureTest {

    private final JavaClasses importedClasses =
            new ClassFileImporter().importPackages("org.egualpam.contexts.observable");

    @Test
    void domain_should_not_depend_on_application_ports() {
        noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAPackage("..ports..")
                .check(importedClasses);
    }

    @Test
    void domain_should_not_depend_on_adapters() {
        noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAPackage("..adapters..")
                .check(importedClasses);
    }

    @Test
    void application_should_not_depend_on_adapters() {
        noClasses().that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAPackage("..adapters..")
                .check(importedClasses);
    }
}
