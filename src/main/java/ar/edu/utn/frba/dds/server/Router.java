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

        RepoColaborador repoColaborador = new RepoColaborador();
        RepoReporte repoReporte = RepoReporte.INSTANCE;
        ColaboradorController colaboradorController = new ColaboradorController(repoColaborador);

        RepoOferta repoOferta = new RepoOferta();
        OfertaController ofertaController = new OfertaController();

        app.error(404, ctx -> {
            ctx.render("errors/error404.hbs"); // Asegúrate de que la ruta sea correcta
        });

        app.error(500, ctx -> {
            throw new SystemException("f");
        });

        app.get("/prueba", ctx -> ctx.result("Hello World"));

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
        app.get("/inicioSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::index);
        app.post("/inicioSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::login);

        // donaciones
        app.get("/donarDinero", Objects.requireNonNull(ServiceLocator.instanceOf(DonarDineroController.class))::index, TipoRol.COLABORADOR_HUMANO,TipoRol.COLABORADOR_JURIDICO);

        // encargarseDeHeladera
        app.get("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::index,TipoRol.COLABORADOR_JURIDICO);
        app.post("/encargarseHeladera", Objects.requireNonNull(ServiceLocator.instanceOf(EncargarseHeladeraController.class))::save,TipoRol.COLABORADOR_JURIDICO);
        // fallaTecnica
        app.get("/fallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::index,TipoRol.COLABORADOR_HUMANO);
        app.get("/crearfallaTecnica", Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::create,TipoRol.COLABORADOR_HUMANO);
        // visualizarPDFs
        app.get("/reportes", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::index,TipoRol.ADMIN);
        app.get("/reportes/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(ReporteController.class))::show,TipoRol.ADMIN);

        app.get("/canjeProductos", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::index,TipoRol.COLABORADOR_HUMANO);

        app.post("/canjearProductos", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::save);


        /* LOGS --- REGISTROS */

        app.get("/crearCuenta", Objects.requireNonNull(ServiceLocator.instanceOf(CrearCuentaController.class))::index);


        app.get("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::index);
        app.post("/cargaMasiva", Objects.requireNonNull(ServiceLocator.instanceOf(CargaMasivaController.class))::cargaMasiva);
        app.get("/registroVulnerable", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroVulnerableController.class))::index);

        // app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);

        app.get("/donarViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::index);
        app.post("/donacionViandas", Objects.requireNonNull(ServiceLocator.instanceOf(DonacionViandaController.class))::save);


        /*---Pantallas de Nahuel (todavía no terminado)---*/
        //todavía está harcodeado y los verbos no están chequeados, estoy probando que levanten las pantallas
        app.get("/misHeladeras",Objects.requireNonNull(ServiceLocator.instanceOf(MisHeladerasController.class))::index);

        app.get("/registroJuridico",Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::index);
        app.post("/registroJuridico",Objects.requireNonNull(ServiceLocator.instanceOf(RegistroJuridicoController.class))::save);

        app.get("/registroHumano",Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);// lo dejo así para probar, después me voy a asegurar que el verbo sea el correcto
        app.get("/distribuirViandas",Objects.requireNonNull(ServiceLocator.instanceOf(DistribuirViandasController.class))::index);


        // Está parte ya estaba hecha pero la comento para revisarla ya que tengo que hacer la pantalla de registroHumano también
       /* app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);
        app.post("/registroHumanoCarga", ctx -> {
            String nombre = ctx.formParam("nombre");
            String apellido = ctx.formParam("apellido");
            String fechaNacimiento = ctx.formParam("fechaNacimiento");
            String provincia = ctx.formParam("provincia");
            String localidad = ctx.formParam("localidad");
            String direccion = ctx.formParam("direccion");
            String altura = ctx.formParam("altura");
            String piso = ctx.formParam("piso");
            String telefono = ctx.formParam("telefono");
            String correo = ctx.formParam("correo");
;
            // Crear un nuevo colaborador con estos datos
            Colaborador colaborador = new Colaborador();


            // Guardar el colaborador en la base de datos o realizar alguna acción
            RepoColaborador.INSTANCE.agregar(colaborador);

            ctx.redirect("/success"); // Redirigir a una página de éxito o mostrar un mensaje
        });
*/

        app.get("/heladeras", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::index);
        app.post("/heladeras/create", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::create);
        app.get("/heladeras/{id}", Objects.requireNonNull(ServiceLocator.instanceOf(HeladeraController.class))::show);
    }

}

