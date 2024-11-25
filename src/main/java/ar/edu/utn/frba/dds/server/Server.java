package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.server.crons.SchedulerMain;
import ar.edu.utn.frba.dds.utils.Init;
import ar.edu.utn.frba.dds.utils.JavalinRenderer;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;

import java.io.IOException;
import java.util.function.Consumer;

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
            app = Javalin.create(config()).start(port);
            Router router = new Router();
            //SchedulerMain.main(new String[]{});
            //Init.iniciar();
            Router.init(Server.app());


        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/public";


            });

            config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {

                //Handlebars handlebars = new Handlebars();
                Handlebars handlebars = JavalinRenderer.configureHandlebars();
                Template template = null;
                try {
                    template = handlebars.compile(
                            "templates/" + path.replace(".hbs", ""));
                    return template.apply(model);
                } catch (IOException e) {
                    e.printStackTrace();
                    context.status(HttpStatus.NOT_FOUND);
                    return "No se encuentra la página indicada...";
                }
            }));
        };
    }
}
