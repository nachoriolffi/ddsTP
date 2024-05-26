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



@Getter
@Setter
public class Colaborador {
    private String nombre;
    private String apellido;
    private List<MedioDeComunicacion> mediosDeComunicacion;
    private Date fechaDeNacimiento;
    private Direccion direccion;
    private List<FormaDeColaboracion> formasDeColaboracion;
    private List<FormaDeColaboracion> colaboracionesRealizadas;
    private CuestionarioRespondido cuestionarioRespondido;
    private Integer razonSocial;
    private TipoJuridisccion tipoJuridisccion;
    private String rubro;
    private TipoPersona tipoPersona;
    private Contacto contacto;
    private API_RecomendacionHeladeras API_RecomendacionHeladeras;// cambiar a recomendacionHeladeras_API
    private Integer puntosTotales;
    private Integer numeroDocumento; //nuevo requerimiento para carga masiva
    private TipoDocumento tipoDocumento; //nuevo requerimiento para carga masiva


    public Colaborador(Integer numeroDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion,TipoPersona tipoPersona)
    { //persona humana
        //fecha de nacimiento y direccion es opcional para persona humana
        this.numeroDocumento = Integer.valueOf(numeroDocumento);
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = new ArrayList<>(mediosDeComunicacion);
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
        this.tipoPersona = tipoPersona;
    }


    public Colaborador(List<MedioDeComunicacion> mediosDeComunicacion, TipoJuridisccion tipoJuridisccion ,TipoPersona tipoPersona)
    { // persona juridica
        this.nombre = null;
        this.apellido = null;
        this.fechaDeNacimiento = null;
        this.numeroDocumento = null;
        this.tipoDocumento = null;

        this.tipoJuridisccion = tipoJuridisccion;// Razon social
        this.tipoPersona = tipoPersona;
        this.mediosDeComunicacion = new ArrayList<>(mediosDeComunicacion);// Es el medio de contacto
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
    }


    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.mediosDeComunicacion.add(medioDeComunicacion);
    }

    public void agregarFormaDeColaboracion(FormaDeColaboracion formaDeColaboracion) {
        this.formasDeColaboracion.add(formaDeColaboracion);
    }

    public void agregarColaboracionRealizada(FormaDeColaboracion formaDeColaboracion) {
        this.colaboracionesRealizadas.add(formaDeColaboracion);
    }

    public void modificarColaborador(Integer numeroDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion, Date fechaDeNacimiento, List<FormaDeColaboracion> formasDeColaboracion)
    {//persona humana -> En este caso el tipo persona no se cambia, las colaboraciones realizadas no se deberian modificar tampoco
        this.fechaDeNacimiento = fechaDeNacimiento;

        this.numeroDocumento = Integer.valueOf(numeroDocumento);
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = new ArrayList<>(mediosDeComunicacion);
        this.formasDeColaboracion = new ArrayList<>(formasDeColaboracion);
    }

    public void modificarColaborador(List<MedioDeComunicacion> mediosDeComunicacion, TipoJuridisccion tipoJuridisccion)
    { // persona juridica -> En este caso el tipo persona no se cambia
        this.tipoJuridisccion = tipoJuridisccion;// Razon social
        this.mediosDeComunicacion = new ArrayList<>(mediosDeComunicacion);// Es el medio de contacto
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
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

