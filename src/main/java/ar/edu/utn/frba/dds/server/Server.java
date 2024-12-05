package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.runnable.RutinaBrokerApertura;
import ar.edu.utn.frba.dds.runnable.RutinaBrokerMovimiento;
import ar.edu.utn.frba.dds.runnable.RutinaBrokerTemperatura;
import ar.edu.utn.frba.dds.utils.Init;
import ar.edu.utn.frba.dds.utils.JavalinRenderer;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.util.function.Consumer;

public class Server {

    private static Javalin app = null;

    //observabilidad
    //public static final DDMetricsUtils metricsUtils = new DDMetricsUtils("tpdds");
    //@Getter
    //public static final StepMeterRegistry registry = metricsUtils.getRegistry();

    // Metricas
    //final var myGauge = registry.gauge("dds.unGauge", new AtomicInteger(0));

    // Config
    //private static final MicrometerPlugin micrometerPlugin = new MicrometerPlugin(config -> config.registry = registry);

    public static Javalin app() {
        if (app == null)
            throw new RuntimeException("Error Al Inicializar APP");
        return app;
    }

    public static void init() {
        if (app == null) {
            int port = Integer.parseInt(System.getProperty("port", "8080"));

//            app = Javalin.create( config -> { config.registerPlugin(micrometerPlugin); })
//                    .start(port);
//            Router router = new Router();
            app = Javalin.create(config())
                    .start(port);
            Router router = new Router();


            RutinaBrokerApertura brokerAperturaRoutine = new RutinaBrokerApertura();
            Thread hiloRecepcionApertura = new Thread(brokerAperturaRoutine);
            hiloRecepcionApertura.start();

            RutinaBrokerTemperatura brokerTemperatura = new RutinaBrokerTemperatura();
            Thread hiloRecepcionTemperatura = new Thread(brokerTemperatura);
            hiloRecepcionTemperatura.start();

            RutinaBrokerMovimiento brokerMovimiento = new RutinaBrokerMovimiento();
            Thread hiloRecepcionMovimiento = new Thread(brokerMovimiento);
            hiloRecepcionMovimiento.start();


            Init.iniciar();
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
