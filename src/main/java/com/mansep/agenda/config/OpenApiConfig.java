package com.mansep.agenda.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Agenda médica virtual", 
        version = "v1.0", 
        description = "API Rest de gestión de horas médicas", 
        contact = @Contact(name = "Manuel Sepúlveda Durán", email = "mansep1991@gmail.com", url = "http://www.mansep.com/")), 
    servers = {
        @Server(url = "http://api.dev.agenda.mansep.com", description = "DEV Server"),
        @Server(url = "http://api.prod.agenda.mansep.com", description = "PROD Server") 
    }
)
public class OpenApiConfig {
}