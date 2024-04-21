package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.cuestionario.TipoPregunta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;

public class TestColaborador {

    private Pregunta pregunta1;
    private Pregunta pregunta2;
    private Pregunta pregunta3;
    private Respuesta respuesta1;
    private Respuesta respuesta2;
    private Respuesta respuesta3;




    private Cuestionario cuestionarioHumano;

    @BeforeEach
    public void setUp() throws Exception {
        pregunta1= new Pregunta("pregunta nombre", true, TipoPregunta.STRING);
        pregunta2= new Pregunta("pregunta apellido", true, TipoPregunta.STRING);
        pregunta3= new Pregunta("pregunta edad", true, TipoPregunta.FECHA);
        cuestionarioHumano= new Cuestionario("cuestionarioHumano","cuestionario para crear una persona humana");

    }

    @Test
    public void testCuestionario(){

        cuestionarioHumano.agregarPregunta(pregunta1);
        cuestionarioHumano.agregarPregunta(pregunta2);
        cuestionarioHumano.agregarPregunta(pregunta3);

        assert cuestionarioHumano.getPreguntas().size()==3;

    }

    public void TestRepuestas(){


    }



}
