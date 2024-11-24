package ar.edu.utn.frba.dds.sast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StaticCodeAnalyzer {

    // Expresión regular para detectar contraseñas hardcodeadas
    private static final String HARD_CODED_PASSWORD_PATTERN = "(?i)(\"password\"|\"passwd\"|\"secret\")\\s*[:=]\\s*\"[^\"]*\"";

    // Expresión regular para detectar uso inseguro de entradas (posible inyección SQL)
    private static final String SQL_INJECTION_PATTERN = "(?i)(exec|execute|select|insert|drop|--|\\*|;|\\/\\*)";

    // Expresión regular para detectar el uso de exec(), system(), Runtime.getRuntime().exec(), etc.
    private static final String EXEC_METHOD_PATTERN = "(?i)(exec|system|Runtime\\.getRuntime\\.exec)";

    // Expresión regular para detectar dependencias inseguras
    private static final String INSECURE_DEPENDENCIES_PATTERN = "(?i)(org\\.apache\\.struts|log4j|commons-dbcp)"; // Ejemplo con bibliotecas inseguras comunes

    // Método para analizar un archivo de código fuente
    public static void analyzeFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("El archivo no existe: " + filePath);
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;

            // Buscar contraseñas hardcodeadas
            if (line.matches(HARD_CODED_PASSWORD_PATTERN)) {
                System.out.println("Vulnerabilidad detectada (contraseña hardcodeada) en línea " + lineNumber + ": " + line);
            }

            // Buscar posibles inyecciones SQL
            if (line.matches(SQL_INJECTION_PATTERN)) {
                System.out.println("Vulnerabilidad detectada (posible inyección SQL) en línea " + lineNumber + ": " + line);
            }

            // Buscar uso de exec() y otros métodos peligrosos
            if (line.matches(EXEC_METHOD_PATTERN)) {
                System.out.println("Vulnerabilidad detectada (uso inseguro de exec) en línea " + lineNumber + ": " + line);
            }

            // Buscar dependencias inseguras
            if (line.matches(INSECURE_DEPENDENCIES_PATTERN)) {
                System.out.println("Vulnerabilidad detectada (dependencia insegura) en línea " + lineNumber + ": " + line);
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporcione la ruta del archivo de código fuente para analizar.");
            return;
        }

        String filePath = args[0];

        try {
            analyzeFile(filePath);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}