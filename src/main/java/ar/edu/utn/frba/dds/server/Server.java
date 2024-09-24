package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.utils.Init;
import ar.edu.utn.frba.dds.utils.JavalinRenderer;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.io.IOException;

public class Server {

    private static Javalin app = null;

    public static Javalin app() {
        if (app == null)

            throw new RuntimeException("Error Al Inicializar APP");
        return app;
    }

    public static void init() {
        if (app == null) {
            int port = Integer.parseInt(System.getProperty("port", "8081"));
            app = Javalin.create().start(port);
            Router router = new Router();
            Init.iniciar();
            router.init(Server.app());


        }
    }

    
}
