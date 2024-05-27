/*
 * @(#)OpenApiConfig.java
 *
 * Copyright (c) (Chile). All rights reserved.
 *
 * All rights to this product are owned by and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.org.ms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig.
 *
 * @author Jose.
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo(
        @Value("${swagger.info.version: Versión de la API}") final String appVersion,
        @Value("${swagger.info.name: Nombre de la API}") final String apiName,
        @Value("${swagger.info.description: Descripción de la API}") final String apiDescripcion,
        @Value("${swagger.info.contact.name: Nombre del líder técnico}") final String contactName,
        @Value("${swagger.info.contact.mail: contacto@email.com}") final String contactEmail) {
        return new OpenAPI()
            .info(new Info()
                .title(apiName)
                .version(appVersion)
                .description(apiDescripcion)
                .contact(new Contact()
                    .name(contactName)
                    .email(contactEmail)));
    }

    @Bean
    public ApiResponse apiResponse() {
        return new ApiResponse();
    }
}
