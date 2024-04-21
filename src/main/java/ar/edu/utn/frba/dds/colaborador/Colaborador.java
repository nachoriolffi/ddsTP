package ar.edu.utn.frba.dds.colaborador;

import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.cuestionario.Respuesta;

import java.util.ArrayList;
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

    public Colaborador( String nombre, String apellido, List<MedioDeComunicacion> medioDeComunicacion, Date fechaDeNacimiento, List<FormaDeColaboracion> formaDeColaboracion, CuestionarioRespondido cuestionarioRespondido, String razonSocial, String rubro, TipoPersona tipoPersona, Contacto contacto)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.medioDeComunicacion = medioDeComunicacion;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.formaDeColaboracion = formaDeColaboracion;
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.razonSocial = razonSocial;
        this.rubro = rubro;
        this.tipoPersona = tipoPersona;
        this.contacto = contacto;
    }





    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.medioDeComunicacion.add(medioDeComunicacion);
    }

    public void agregarFormaDeColaboracion(FormaDeColaboracion formaDeColaboracion) {
        this.formaDeColaboracion.add(formaDeColaboracion);
    }

    public void modificarColaborador(String nombre, String apellido, Date fechaDeNacimiento, CuestionarioRespondido cuestionarioRespondido, String razonSocial, String rubro, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.medioDeComunicacion = new ArrayList<MedioDeComunicacion>();
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.formaDeColaboracion = new ArrayList<FormaDeColaboracion>();
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.razonSocial = razonSocial;
        this.rubro = rubro;
        this.tipoPersona = tipoPersona;
        this.contacto = contacto;
    }

   /* public void cargarRespuestas(CuestionarioRespondido cuestionarioRespondido) {
        for (Respuesta respuesta : cuestionarioRespondido.getRespuestas()) {
            { if(respuesta.esObligatoria()){
                if(respuesta.getOpcion().isEmpty() && respuesta.getRespuestaAbierta().isEmpty()){
                     throw new RuntimeException("La respuesta es obligatoria");
                }
                else
            }

            }
        }
    }*/

}

