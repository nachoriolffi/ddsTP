package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.cuestionario.*;
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
    private CuestionarioRespondido cuestionarioRespondido;
    private Colaborador colaborador;

    private Contacto contacto;



    private Cuestionario cuestionarioHumano;

    @BeforeEach
    public void setUp() throws Exception {
        pregunta1= new Pregunta("nombre", true, TipoPregunta.STRING);
        pregunta2= new Pregunta("apellido", true, TipoPregunta.STRING);
        pregunta3= new Pregunta("fechaDeNacimiento", true, TipoPregunta.FECHA);
        cuestionarioHumano= new Cuestionario("cuestionarioHumano","cuestionario para crear una persona humana");
        respuesta1= new Respuesta(pregunta1,"federico");
        respuesta2= new Respuesta(pregunta2,"perez");
        respuesta3= new Respuesta(pregunta3,"27/05/1999");
        cuestionarioRespondido= new CuestionarioRespondido(cuestionarioHumano);
        contacto= new Contacto();
    }

    @Test
    public void testCuestionario(){

        cuestionarioHumano.agregarPregunta(pregunta1);
        cuestionarioHumano.agregarPregunta(pregunta2);
        cuestionarioHumano.agregarPregunta(pregunta3);

        assert cuestionarioHumano.getPreguntas().size()==3;

    }
    @Test
    public void TestRepuestas(){

        cuestionarioRespondido.agregarRespuesta(respuesta1);
        cuestionarioRespondido.agregarRespuesta(respuesta2);
        cuestionarioRespondido.agregarRespuesta(respuesta3);

        assert cuestionarioRespondido.getRespuestas().size()==3;

    }

    @Test
    public void TestColaborador(){

        colaborador= new Colaborador();

        cuestionarioHumano.agregarPregunta(pregunta1);
        cuestionarioHumano.agregarPregunta(pregunta2);
        cuestionarioHumano.agregarPregunta(pregunta3);
        respuesta1.setPregunta(pregunta1);
        respuesta2.setPregunta(pregunta2);
        respuesta3.setPregunta(pregunta3);
        cuestionarioRespondido.agregarRespuesta(respuesta1);
        cuestionarioRespondido.agregarRespuesta(respuesta2);
        cuestionarioRespondido.agregarRespuesta(respuesta3);
        cuestionarioRespondido.setCuestionario(cuestionarioHumano);

        colaborador.cargarRespuestas(cuestionarioRespondido);

        //correcto si nombre, apellido y telefono no son null
        assert colaborador.getNombre()!=null;
        assert colaborador.getApellido()!=null;
        assert colaborador.getFechaDeNacimiento()!=null;
    }



}
