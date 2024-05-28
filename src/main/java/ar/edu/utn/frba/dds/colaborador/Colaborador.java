package ar.edu.utn.frba.dds.colaborador;

import ar.edu.utn.frba.dds.colaborador.formasColab.Rubro;
import ar.edu.utn.frba.dds.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.recomendacionPuntos.APIRecomendacionHeladeras;
import ar.edu.utn.frba.dds.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.contacto.Contacto;
import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.recomendacionPuntos.AdapRecomendacionPuntos;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.ubicacionGeografica.Direccion;

import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
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
    private List<FormaDeColaboracion> colaboracionesRealizadas;
    private CuestionarioRespondido cuestionarioRespondido;
    private Integer razonSocial;
    private TipoJuridisccion tipoJuridisccion;
    private Rubro rubro;
    private TipoPersona tipoPersona;
    private Contacto contacto;
    private APIRecomendacionHeladeras apiRecomendacionHeladeras;
    private Double puntosTotales;
    private Double puntosTotalesUsados;
    private Integer numeroDocumento; //nuevo requerimiento para carga masiva
    private TipoDocumento tipoDocumento; //nuevo requerimiento para carga masiva
    private List<Oferta> ofertasRegistradas;

    // CONSTRUCTORES

    public Colaborador() {
        this.mediosDeComunicacion = new ArrayList<>();
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
        this.puntosTotalesUsados= (double) 0;
        this.apiRecomendacionHeladeras = new AdapRecomendacionPuntos();
    }

    public Colaborador(String nombre,String apellido,List<FormaDeColaboracion> hechas){
        this.nombre = nombre;
        this.apellido = apellido;
        this.colaboracionesRealizadas = hechas;
        this.puntosTotales= (double) 0;
        this.puntosTotalesUsados= (double) 0;
    }

    public Colaborador(String nombre,String apellido,Double puntosTotales){
        this.nombre=nombre;
        this.apellido=apellido;
        this.puntosTotales=puntosTotales;
        this.puntosTotalesUsados= (double) 0;
        this.ofertasRegistradas = new ArrayList<Oferta>();
    }

    public Colaborador(Integer numeroDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.numeroDocumento = Integer.valueOf(numeroDocumento);
        this.tipoDocumento = tipoDocumento;
        this.colaboracionesRealizadas = new ArrayList<>();
        this.puntosTotalesUsados= (double) 0;
    }

    public Colaborador(String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion, List<FormaDeColaboracion> formasDeColaboracion, CuestionarioRespondido cuestionarioRespondido, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.formasDeColaboracion = formasDeColaboracion;
        this.puntosTotalesUsados= (double) 0;
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.tipoPersona = tipoPersona;
        this.contacto = contacto;
        this.mediosDeComunicacion = new ArrayList<>();
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

    public void modificarColaborador(String nombre, String apellido, Date fechaDeNacimiento, CuestionarioRespondido cuestionarioRespondido, Integer razonSocial, String rubro, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = new ArrayList<>();
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.formasDeColaboracion = new ArrayList<>();
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.razonSocial = razonSocial;
        //this.rubro = rubro;
        this.tipoPersona = tipoPersona;
        this.contacto = contacto;
    }

    public void sumarPuntos(Double puntos) {
        this.puntosTotales += puntos;
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

    public void sumarPuntosUsados(Double aDouble) {
        this.puntosTotalesUsados += aDouble;
    }

    public Double puntosActualesDisponibles(){
        return this.puntosTotales - this.puntosTotalesUsados;
    }
    public void agregarOfertasCanjeadas(List<Oferta> ofertas){
        this.ofertasRegistradas.addAll(ofertas);
    }

    public List<Coordenada> obtenerPuntosRecomendadosParaHeladera(Double longitud, Double latitud, Integer radio) throws IOException {
        return apiRecomendacionHeladeras.recomendarPuntos(longitud,latitud,radio);
    }
}

