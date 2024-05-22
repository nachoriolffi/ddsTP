package ar.edu.utn.frba.dds.colaborador;

import ar.edu.utn.frba.dds.recomendaciones.API_RecomendacionHeladeras;
import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.utils.Direccion;

import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter

public class Colaborador {
    private String nombre;
    private String apellido;
    private List<MedioDeComunicacion> mediosDeComunicacion;
    private Date fechaDeNacimiento;
    private Direccion direccion;
    private List<FormaDeColaboracion> formasDeColaboracion;
    private CuestionarioRespondido cuestionarioRespondido;
    private Integer razonSocial;
    private TipoJuridisccion tipoJuridisccion;
    private String rubro;
    private TipoPersona tipoPersona;
    private Contacto contacto;
    private API_RecomendacionHeladeras API_RecomendacionHeladeras;
    private Integer puntosTotales;
    private Integer numeroDocumento;
    private TipoDocumento tipoDocumento;


    public Colaborador() {

    }

    public Colaborador(Integer numeroDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.numeroDocumento = Integer.valueOf(numeroDocumento);
        this.tipoDocumento = tipoDocumento;
    }

    public Colaborador(String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion, List<FormaDeColaboracion> formasDeColaboracion, CuestionarioRespondido cuestionarioRespondido, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.formasDeColaboracion = formasDeColaboracion;

        this.cuestionarioRespondido = cuestionarioRespondido;
        this.tipoPersona = tipoPersona;
        this.contacto = contacto;
        this.mediosDeComunicacion = new ArrayList<>();
        this.formasDeColaboracion = new ArrayList<>();
    }

    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.mediosDeComunicacion.add(medioDeComunicacion);
    }

    public void agregarFormaDeColaboracion(FormaDeColaboracion formaDeColaboracion) {
        this.formasDeColaboracion.add(formaDeColaboracion);
    }

    public void modificarColaborador(String nombre, String apellido, Date fechaDeNacimiento, CuestionarioRespondido cuestionarioRespondido, Integer razonSocial, String rubro, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = new ArrayList<>();
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.formasDeColaboracion = new ArrayList<>();
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
                if (cuestionarioRespondido.getRespuestas().get(i).getPregunta().getNombre().equals("fechaDeNacimiento")) {
                    campo.set(this, StringToFecha(respuesta));
                    continue;
                }
                campo.set(this, respuesta);
            } catch (NoSuchFieldException | ParseException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Date StringToFecha(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return fechaDate;
    }

}

