package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoPregunta;
import org.junit.jupiter.api.Test;

public class CuestionarioPersistencia {


    @Test
    public void persistirCuestionario() {
        Cuestionario cuestionario = new Cuestionario();

        //creo las preguntas
        Pregunta pregunta1 = new Pregunta();
        Pregunta pregunta2 = new Pregunta();

        pregunta1.setNombre("Pregunta 1");
        pregunta2.setNombre("Pregunta 2");
        pregunta1.setEsObligatoria(true);
        pregunta2.setEsObligatoria(false);
        pregunta1.setTipoPregunta(TipoPregunta.RESPUESTAUNICA);
        pregunta2.setTipoPregunta(TipoPregunta.RESPUESTAUNICA);

        //agrego las preguntas al cuestionario
        cuestionario.agregarPregunta(pregunta1);
        cuestionario.agregarPregunta(pregunta2);

        RepoPregunta repoPregunta = new RepoPregunta();

        repoPregunta.agregar(pregunta1);
        repoPregunta.agregar(pregunta2);


        //persisto el cuestionario
        RepoCuestionario repoCuentionario = new RepoCuestionario();

        repoCuentionario.agregar(cuestionario);

    }
}
