package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.cuestionario.*;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCuestionarioRespondido;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoPregunta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRespuesta;
import org.junit.jupiter.api.Test;

public class CuestionarioPersistencia {

    @Test
    public void persistirCuestionario() {
        Cuestionario cuestionario = new Cuestionario();

        // Create questions
        Pregunta pregunta1 = new Pregunta();
        Pregunta pregunta2 = new Pregunta();
        Respuesta respuesta1 = new Respuesta();
        CuestionarioRespondido cuestionarioRespondido = new CuestionarioRespondido();

        pregunta1.setNombre("Pregunta 1");
        pregunta2.setNombre("Pregunta 2");
        pregunta1.setEsObligatoria(true);
        pregunta2.setEsObligatoria(false);
        pregunta1.setTipoPregunta(TipoPregunta.RESPUESTAUNICA);
        pregunta2.setTipoPregunta(TipoPregunta.RESPUESTAUNICA);

        respuesta1.setRespuestaAbierta("fede ta re loquito");
        respuesta1.setPregunta(pregunta1);

        // Add questions to the questionnaire
        cuestionario.agregarPregunta(pregunta1);
        cuestionario.agregarPregunta(pregunta2);

        RepoPregunta repoPregunta = new RepoPregunta();
        repoPregunta.agregar(pregunta1);
        repoPregunta.agregar(pregunta2);

        RepoCuestionario repoCuestionario = new RepoCuestionario();
        repoCuestionario.agregar(cuestionario);

        // Save the Cuestionario entity before associating it with CuestionarioRespondido
        cuestionarioRespondido.setCuestionario(cuestionario);
        cuestionarioRespondido.agregarRespuesta(respuesta1);

        RepoCuestionarioRespondido repoCuestionarioRespondido = new RepoCuestionarioRespondido();
        repoCuestionarioRespondido.agregar(cuestionarioRespondido);

        // Create responses

        RepoRespuesta repoRespuesta = new RepoRespuesta();
        repoRespuesta.agregar(respuesta1);
    }



    @Test
    public void borroCuestionarioYsusPreguntas(){
        RepoCuestionario repoCuestionario = new RepoCuestionario();
        Cuestionario cuestionario = repoCuestionario.buscar(1L);
        repoCuestionario.eliminar(cuestionario);

    }

    @Test
    public void modificarCuestionario(){
        RepoCuestionario repoCuestionario = new RepoCuestionario();
        Cuestionario cuestionario = repoCuestionario.buscar(1L);
        cuestionario.setNombreCuestionario("Cuestionario Modificado");
        repoCuestionario.modificar(cuestionario);
    }

    @Test
    public void borrarCuestionarioRespondido(){

        RepoCuestionarioRespondido repoCuestionarioRespondido = new RepoCuestionarioRespondido();
        repoCuestionarioRespondido.eliminar(repoCuestionarioRespondido.buscar(1L));
    }


}
