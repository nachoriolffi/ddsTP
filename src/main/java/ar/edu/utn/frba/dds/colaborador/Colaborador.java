package ar.edu.utn.frba.dds.colaborador;

import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.cuestionario.CuestionarioRespondido;

import java.util.Date;
import java.util.List;

public class Colaborador {
    private String nombre;
    private String apellido;

    private List<MedioDeComunicacion> medioDeComunicacion;

    private Date fechaDeNacimiento;

    private List<FormaDeColaboracion> formaDeColaboracion;

    private CuestionarioRespondido cuestionarioRespondido;

    private String razonSocial;

    private String rubro;

    private TipoPersona tipoPersona;

    private Contacto contacto;

    public Colaborador(CuestionarioRespondido cuestionario){
        // necesito iterar las respuestas del cuestionario y asignarlas a las variables de la clase


    }

    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.medioDeComunicacion.add(medioDeComunicacion);
    }

    public void agregarFormaDeColaboracion(FormaDeColaboracion formaDeColaboracion) {
        this.formaDeColaboracion.add(formaDeColaboracion);
    }

}
