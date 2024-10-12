package ar.edu.utn.frba.dds.utils;


import com.github.jknack.handlebars.Handlebars;
import io.javalin.http.Context;
import io.javalin.rendering.FileRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class JavalinRenderer implements FileRenderer {
    private Map<String, FileRenderer> renderers = new HashMap<>();

    public JavalinRenderer register(String extension, FileRenderer renderer) {
        renderers.put(extension, renderer);
        return this;
    }

    @NotNull
    @Override
    public String render(@NotNull String s, @NotNull Map<String, ?> map, @NotNull Context context) {
        String extension = s.substring(s.lastIndexOf(".") + 1);
        return renderers.get(extension).render(s, map, context);
    }

    public static Handlebars configureHandlebars() {
        Handlebars handlebars = new Handlebars();

        handlebars.registerHelper("isEqual", (context, options) -> {
            if (options.params.length < 2) {
                return options.inverse(context); // Not enough parameters, return the inverse block
            }

            // Get the parameters to compare
            Object param1 = options.param(0);
            Object param2 = options.param(1);

            // Compare the parameters
            if (param1 != null && param1.equals(param2)) {
                return options.fn(context); // If they are equal, return the positive block
            } else {
                return options.inverse(context); // If they are not equal, return the inverse block
            }
        });

        return handlebars;
    }
}