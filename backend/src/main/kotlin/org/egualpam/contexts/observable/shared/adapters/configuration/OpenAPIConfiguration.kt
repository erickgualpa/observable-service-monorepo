package org.egualpam.contexts.observable.shared.adapters.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfiguration {
  @Bean
  fun openAPI(): OpenAPI {
    return OpenAPI()
        .info(
            Info().title("Order Service"),
        )
  }
}
