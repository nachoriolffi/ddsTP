package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.congif.ServiceLocator;
import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import io.javalin.Javalin;

import javax.transaction.SystemException;
import java.util.Objects;

public class Router {

    public static void init(Javalin app) {

        app.error(404, ctx -> {
            ctx.render("errors/error404.hbs");
        });

        app.error(403, ctx -> {
            ctx.render("errors/error403.hbs");
        });

        app.error(500, ctx -> {
            throw new SystemException("f algo rompimos");
        });

        // error403
        app.get("/error403", ctx -> {
            ctx.render("errors/error403.hbs");
        });

        // error404
        app.get("/error404", ctx -> {
            ctx.render("errors/error404.hbs");
        });
        // error500
        app.get("/error500", ctx -> {
            ctx.render("errors/error500.hbs");
        });
        // error401
        app.get("/error401", ctx -> {
            ctx.render("errors/error401.hbs");
        });

        app.get("/", Objects.requireNonNull(ServiceLocator.instanceOf(IndexController.class))::index);

        // iniciarSesion
        app.get("/iniciarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::index);
        app.post("/iniciarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::login);
        app.post("/cerrarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::logout);

        // donacion de dinero
        app.get("/donacionDinero", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::index, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/donacionDinero", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::save, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/donacionDinero/{id}/cancelar", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::cancelar, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        // encargarseDeHeladera
        app.get("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::index, TipoRol.COLABORADOR_JURIDICO);
        app.post("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::save, TipoRol.COLABORADOR_JURIDICO);
        app.post("/buscarPuntosRecomendacion", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::searchPoints);
        // fallaTecnica
        app.get("/fallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::index, TipoRol.COLABORADOR_HUMANO);
        app.post("/fallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::save, TipoRol.COLABORADOR_HUMANO);
        // visualizarPDFs
        app.get("/reportes", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::index, TipoRol.ADMIN);
        app.get("/reportes/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::show, TipoRol.ADMIN);

        app.get("/canjeProductos", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::index, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/cargarProducto", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::save);
        app.post("/canjearProducto", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::canjear);
        app.get("/ofertasCanjeadas", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::verMisOfertas);

        app.get("/crearCuenta", Objects.requireNonNull(ServiceLocator.instanceOf(CrearCuentaController.class))::index);
        app.post("/crearCuenta", Objects.requireNonNull(ServiceLocator.instanceOf(CrearCuentaController.class))::save);

        // carga masiva de datos
        app.get("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::index, TipoRol.ADMIN);
        app.post("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::cargaMasiva, TipoRol.ADMIN);

        app.get("/registroVulnerable", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroVulnerableController.class))::index, TipoRol.COLABORADOR_HUMANO);
        app.post("/registrarPersona", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroVulnerableController.class))::create, TipoRol.COLABORADOR_HUMANO);
        // app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);

        app.get("/donarViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::index);
        app.post("/donarViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::save);

        app.get("/suscripcionesAHeladeras", Objects.requireNonNull(ServiceLocator.instanceOf(suscripcionesAHeladerasController.class))::index, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/suscripcionesAHeladeras", Objects.requireNonNull(ServiceLocator.instanceOf(suscripcionesAHeladerasController.class))::save);
        app.post("/suscripcionesAHeladeras/delete", Objects.requireNonNull(ServiceLocator.instanceOf(suscripcionesAHeladerasController.class))::delete);

        app.get("/registroJuridico", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::index);
        app.post("/registroJuridico", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::create);

        app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);
        app.post("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::create);
        //app.post("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::save);

        app.get("/distribuirViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DistribuirViandasController.class))::index);
        app.post("/distribuirViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DistribuirViandasController.class))::save);

        app.get("/heladeras", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::index);
        app.post("/heladeras/create", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::create);
        app.get("/heladeras/{heladeraId}", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::show);

        app.get("/cargaTecnico", Objects.requireNonNull(ServiceLocator.instanceOf(TecnicoController.class))::index);
        app.post("/cargaTecnico", Objects.requireNonNull(ServiceLocator.instanceOf(TecnicoController.class))::save);
        app.get("/mapaHeladeras", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::showMap);
        app.post("/cargarModelo", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::createModel);
        app.post("heladeras/modificar", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::edit);
        app.post("heladeras/darDeBaja", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::delete);
        app.post("/suscribirse/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::subscribe);

        app.get("/verPerfil", Objects.requireNonNull(ServiceLocator.instanceOf(UserController.class))::index);
        app.post("/guardarCambiosPerfil", Objects.requireNonNull(ServiceLocator.instanceOf(UserController.class))::edit);

        app.post("/darDeBaja", Objects.requireNonNull(ServiceLocator.instanceOf(ColaboradorController.class))::delete);
        app.get("/verColaboradores", Objects.requireNonNull(ServiceLocator.instanceOf(ColaboradorController.class))::index);

        app.get("/servicioUbicacion", Objects.requireNonNull(ServiceLocator.instanceOf(ServicioPuntoDonacionController.class))::index);
        app.post("/puntosRecomendadosServicio", Objects.requireNonNull(ServiceLocator.instanceOf(ServicioPuntoDonacionController.class))::show);
        app.get("/IncidentesTecnico", Objects.requireNonNull(ServiceLocator.instanceOf(TecnicoController.class))::show);
        app.get("/IncidentesTecnico/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(IncidenteController.class))::index);
        app.get("/verFallasTecnicas", Objects.requireNonNull(ServiceLocator.instanceOf(IncidenteController.class))::fallasTecnicas);
        app.get("/perfilTecnico", Objects.requireNonNull(ServiceLocator.instanceOf(TecnicoController.class))::perfil);

        app.get("/visita", Objects.requireNonNull(ServiceLocator.instanceOf(VisitaController.class))::index);
        app.post("/visita", Objects.requireNonNull(ServiceLocator.instanceOf(VisitaController.class))::create);


    }

}
