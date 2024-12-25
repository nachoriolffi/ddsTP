package ar.edu.utn.frba.dds.models.entities.generadorCodigo;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTarjeta;
import java.security.SecureRandom;

public class GeneradorDeCodigo {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 11;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final RepoTarjeta repoTarjetas = RepoTarjeta.INSTANCE;

    // Instancia única de GeneradorDeCodigo
    private static GeneradorDeCodigo instancia;

    // Constructor privado para evitar instanciación externa
    private GeneradorDeCodigo() {
    }

    // Método para obtener la única instancia de la clase
    public static synchronized GeneradorDeCodigo getInstance() {
        if (instancia == null) {
            instancia = new GeneradorDeCodigo();
        }
        return instancia;
    }

    public String generarCodigoUnico() {
        String codigo;
        do {
            codigo = generarCodigoAleatorio();
        } while (existeEnRepo(codigo));
        return codigo;
    }

    private String generarCodigoAleatorio() {
        StringBuilder codigo = new StringBuilder(LONGITUD_CODIGO);
        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            codigo.append(CARACTERES.charAt(RANDOM.nextInt(CARACTERES.length())));
        }
        return codigo.toString();
    }

    private boolean existeEnRepo(String codigo) {
        return repoTarjetas.buscarTodos().stream()
                .anyMatch(tarjeta -> tarjeta.getCodigo().equals(codigo));
    }
}
