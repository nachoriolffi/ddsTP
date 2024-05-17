package ar.edu.utn.frba.dds.colaborador;

import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.cuestionario.Respuesta;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;


@Setter
@Getter

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

    private TipoDocumento tipoDocumento;

    private String numeroDocumento;

    public Colaborador( )
    {
        this.medioDeComunicacion = new ArrayList<MedioDeComunicacion>();
        this.formaDeColaboracion = new ArrayList<FormaDeColaboracion>();
    }

    public Colaborador( TipoDocumento tipoDocumento,String numeroDocumento,String nombre, String apellido,String contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.medioDeComunicacion = new ArrayList<MedioDeComunicacion>();
        this.formaDeColaboracion = new ArrayList<FormaDeColaboracion>();
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.tipoPersona = tipoPersona;
        this.contacto =new Contacto();
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
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


    public void cargarRespuestas(CuestionarioRespondido cuestionarioRespondido) {
        for (int i = 0; i < cuestionarioRespondido.getRespuestas().size(); i++) {
            String respuesta = cuestionarioRespondido.getRespuestas().get(i).getRespuestaAbierta();
            Class<Colaborador> claseColab = Colaborador.class;
            try {
                Field campo = claseColab.getDeclaredField(cuestionarioRespondido.getRespuestas().get(i).getPregunta().getNombre());
                if(cuestionarioRespondido.getRespuestas().get(i).getPregunta().getNombre()=="fechaDeNacimiento")
                {
                    campo.set(this, StringToFecha(respuesta));
                    continue;
                }
                campo.set(this, respuesta);
            }
            catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Date StringToFecha(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return fechaDate;
    }
}

