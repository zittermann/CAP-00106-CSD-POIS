package ar.com.grupoesfera.csd.pois.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "POIS",
                description = "API para adminitrar puntos de interés.",
                contact = @Contact(
                        name = "Equipo 4"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
}
