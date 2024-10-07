package ar.edu.utn.frba.dds.models.entities.usuario;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
    COLABORADOR_JURIDICO,
    COLABORADOR_HUMANO,
    TECNICO,
    ADMIN
}
