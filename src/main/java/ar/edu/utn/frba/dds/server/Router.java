package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.congif.ServiceLocator;
import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoReporte;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import javassist.NotFoundException;

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
            throw new SystemException("f");
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

        // iniciarSesion
        app.get("/iniciarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::index);
        app.post("/iniciarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::login);
        app.post("/cerrarSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::logout);

        // donacion de dinero
        app.get("/donacionDinero", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::index, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/donacionDinero", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::save, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/donacionDinero/{id}/cancelar", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::cancelar, TipoRol.COLABORADOR_HUMANO,TipoRol.COLABORADOR_JURIDICO);
        // encargarseDeHeladera
        app.get("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::index, TipoRol.COLABORADOR_JURIDICO);
        app.post("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::save, TipoRol.COLABORADOR_JURIDICO);
        // fallaTecnica
        app.get("/fallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::index, TipoRol.COLABORADOR_HUMANO);
        app.get("/crearfallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::create, TipoRol.COLABORADOR_HUMANO);
        // visualizarPDFs
        app.get("/reportes", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::index, TipoRol.ADMIN);
        app.get("/reportes/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::show, TipoRol.ADMIN);

        app.get("/canjeProductos", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::index, TipoRol.COLABORADOR_HUMANO, TipoRol.COLABORADOR_JURIDICO);
        app.post("/canjearProductos", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::save);

        app.get("/crearCuenta", Objects.requireNonNull(ServiceLocator.instanceOf(CrearCuentaController.class))::index);
        app.post("/crearCuenta", Objects.requireNonNull(ServiceLocator.instanceOf(CrearCuentaController.class))::save);

        // carga masiva de datos
        app.get("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::index, TipoRol.ADMIN);
        app.post("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::cargaMasiva, TipoRol.ADMIN);

        app.get("/registroVulnerable", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroVulnerableController.class))::index, TipoRol.COLABORADOR_HUMANO);

        // app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);

        app.get("/donarViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::index);
        app.post("/donarViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::save);


        /*---Pantallas de Nahuel (todavía no terminado)---*/
        //todavía está harcodeado y los verbos no están chequeados, estoy probando que levanten las pantallas
        app.get("/misHeladeras", Objects.requireNonNull(ServiceLocator.instanceOf(MisHeladerasController.class))::index);

        app.get("/registroJuridico", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::index);
        app.post("/registroJuridico", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::save);

        app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);
        app.post("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::create);

        app.get("/distribuirViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DistribuirViandasController.class))::index);
        app.post("/distribuirViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DistribuirViandasController.class))::save);

        app.get("/heladeras", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::index);
        app.post("/heladeras/create", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::create);
        app.get("/heladeras/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::show);
    }

}
