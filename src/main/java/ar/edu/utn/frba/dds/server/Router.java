package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.congif.ServiceLocator;
import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import io.javalin.Javalin;
import javax.transaction.SystemException;
import java.util.Objects;

public class Router {

    public static void init(Javalin app) {

        RepoColaborador repoColaborador = new RepoColaborador();
        ColaboradorController colaboradorController = new ColaboradorController(repoColaborador);
        
        RepoOferta repoOferta = new RepoOferta();
        OfertaController ofertaController = new OfertaController();
        
        app.error(404, ctx -> {
            throw new SystemException("No se encontro la pagina solicitada");
        });

        app.error(500, ctx -> {
            throw new SystemException("f");
        });
        
        app.get("/prueba", ctx -> ctx.result("Hello World"));


        //app.get("/heladera", ServiceLocator.instanceOf(HeladeraController.class)::index)

        // iniciarSesion
        app.get("/inicioSesion", Objects.requireNonNull(ServiceLocator.instanceOf(InicioSesionController.class))::index);
        app.post("/inicioSesion",ctx ->{
           String correoElectronico = ctx.formParam("correoElectronico");
           String clave = ctx.formParam(("clave"));
        });

        // fallaTecnica
        //app.get("/fallaTecnica",Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::index);
        //app.post("/fallaTecnica",Objects.requireNonNull(ServiceLocator.instanceOf(FallaTecnicaController.class))::save);
        // visualizarPDFs
        //app.get("/pdfViewer",Objects.requireNonNull(ServiceLocator.instanceOf(pdfViewerController.class))::index);


        app.get("/colaboradores", colaboradorController::index);

        app.get("/ofertas", Objects.requireNonNull(ServiceLocator.instanceOf(OfertaController.class))::index);

        app.get("/registroHumano", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroHumanoController.class))::index);
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

            // Crear un nuevo colaborador con estos datos
            Colaborador colaborador = new Colaborador();


            // Guardar el colaborador en la base de datos o realizar alguna acción
           RepoColaborador.INSTANCE.agregar(colaborador);

            ctx.redirect("/success"); // Redirigir a una página de éxito o mostrar un mensaje
        });

        app.get("/registroVulnerable", Objects.requireNonNull(ServiceLocator.instanceOf(RegistroVulnerableController.class))::index);
    }
    
    }

