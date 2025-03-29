package com.github.syndexmx.demopetclinic.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo Pet-Clinic API",
                description = "API демо сервиса Пет-Клиники",
                version = "1.0.0",
                contact = @Contact(
                        name = "Шаповалов Максим",
                        url = "https://github.com/syndexmx"
                )
        )
)
public class OpenApiConfig {
}
