package org.egualpam.contexts.observable.shared.adapters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "org.egualpam.contexts.observable.shared.adapters.configuration",
                "org.egualpam.contexts.observable.wallet.adapters.configuration",
                "org.egualpam.contexts.observable.wallet.adapters.in.controllers",
                "org.egualpam.contexts.observable.order.adapters.configuration",
                "org.egualpam.contexts.observable.order.adapters.in"
        }
)
public class SharedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharedApplication.class, args);
    }
}

