package com.sousa.meal_planner.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nuno Sousa",
                        email = "geral@nunosousa.pt",
                        url = "https://nunosousa.pt"
                ),
                description = "OpenApi documentation for Meal Planner",
                title = "OpenApi specification - Nuno Sousa",
                version = "1.0",
                license = @License(
                        name = "Licensed by Nuno Sousa",
                        url = "https://nunosousa.pt"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8889"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://meals.nunosousa.pt"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
public class OpenApiConfig {
}
