package com.mansep.agenda.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Agenda médica virtual", 
        version = "v1.0", 
        description = "API Rest de gestión de horas médicas - Trabajo de titulo", 
        contact = @Contact(name = "Manuel Sepúlveda Durán", email = "mansep1991@gmail.com", url = "http://www.mansep.com/")), 
    servers = {
        @Server(url = "https://agendamedica-ms.herokuapp.com", description = "DEV Server")
    }
)
public class OpenApiConfig {
}