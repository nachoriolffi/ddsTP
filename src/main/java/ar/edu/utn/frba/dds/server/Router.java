package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.ColaboradorController;
import ar.edu.utn.frba.dds.controllers.OfertaController;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import io.javalin.Javalin;
import javax.transaction.SystemException;

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


        //app.get("/heladera", ServiceLocator.instanceOf(HeladeraController.class)::index);
                
                

        app.get("/colaboradores", colaboradorController::index);

        app.get("/ofertas", ofertaController::index);
       
    }
    
    }

