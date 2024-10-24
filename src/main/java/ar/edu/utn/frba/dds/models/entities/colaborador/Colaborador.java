package ar.edu.utn.frba.dds.models.entities.colaborador;

import ar.edu.utn.frba.dds.models.converters.FormaDeColaboracionConverter;
import ar.edu.utn.frba.dds.models.converters.MedioComunicacionAtributeConvertere;
import ar.edu.utn.frba.dds.models.entities.colaborador.calculoPuntos.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.observer.IObserverColaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.IRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.AServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Table(name = "colaborador")
public class Colaborador extends IObserverColaborador {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(255)")
    private String apellido;

    @Convert(converter = MedioComunicacionAtributeConvertere.class)
    @ElementCollection(targetClass = String.class)
    private List<MedioDeComunicacion> mediosDeComunicacion;

    @Column(name = "fechaDeNacimiento")
    private LocalDate fechaDeNacimiento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    //@OneToMany
    //@JoinColumn(name = "id_FormasColaboracion")
    //@Transient
    @Convert(converter = FormaDeColaboracionConverter.class)
    @ElementCollection(targetClass = String.class) // de esta manera puedo cargar las formas de colaboracion que la persona elige y ademas me ayuda a poder asignarle la tarjeta en base a la forma que elige.
    //ademas podemos usar el factory que tenemos creado de esto para el caso de tener que usarlo solo necesitamos el string y en abse a eso creamos la intancia de la colaboracion
    private List<TipoColaboracion> formasDeColaboracion;

    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormaDeColaboracion> colaboracionesRealizadas;

    @ManyToOne
    @JoinColumn(name = "cuestionario_id")
    private CuestionarioRespondido cuestionarioRespondido;

    @Column(name = "razonSocial", columnDefinition = "VARCHAR(255)")
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoJuridiccion")
    private TipoJuridiccion tipoJuridiccion;

    @OneToOne
    @JoinColumn(name = "rubroColaborador_id")
    private RubroColaborador rubroColaborador;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "colaborador_id")
    private List<Contacto> contacto;

    @Transient
    private IRecomendacionPuntos iRecomendacionPuntos;

    @Column(name = "puntosTotalesUsados")
    private Double puntosTotalesUsados;

    @Column(name = "numeroDocumento")
    private Integer numeroDocumento; //nuevo requerimiento para carga masiva

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento; //nuevo requerimiento para carga masiva

    @ManyToMany
    @JoinColumn(name = "oferta_id")
    private List<Oferta> ofertasRegistradas;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Colaborador() {
        this.mediosDeComunicacion = new ArrayList<>();
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
        this.puntosTotalesUsados = (double) 0;
        this.iRecomendacionPuntos = new AServicioRecomendacionPuntos();
        this.contacto = new ArrayList<>();
    }

    public Colaborador(String nombre, String apellido, List<FormaDeColaboracion> hechas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.colaboracionesRealizadas = hechas;
        this.puntosTotalesUsados = (double) 0;
    }

    public Colaborador(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosTotalesUsados = (double) 0;
        this.ofertasRegistradas = new ArrayList<Oferta>();
    }

    public Colaborador(Integer numeroDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.colaboracionesRealizadas = new ArrayList<>();
        this.puntosTotalesUsados = (double) 0;
    }

   /* public Colaborador(String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion, List<FormaDeColaboracion> formasDeColaboracion, CuestionarioRespondido cuestionarioRespondido, TipoPersona tipoPersona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.formasDeColaboracion = formasDeColaboracion;
        this.puntosTotalesUsados = (double) 0;
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.tipoPersona = tipoPersona;
        this.contacto = new ArrayList<>();
        this.mediosDeComunicacion = new ArrayList<>();
        this.formasDeColaboracion = new ArrayList<>();
        this.colaboracionesRealizadas = new ArrayList<>();
    }*/

    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.mediosDeComunicacion.add(medioDeComunicacion);
    }

    /*public void agregarFormaDeColaboracion(FormaDeColaboracion formaDeColaboracion) {
        this.formasDeColaboracion.add(formaDeColaboracion);
    }*/

    public void agregarColaboracionRealizada(FormaDeColaboracion formaDeColaboracion) {
        this.colaboracionesRealizadas.add(formaDeColaboracion);
        formaDeColaboracion.setColaborador(this);
    }

    public void modificarColaborador(String nombre, String apellido, LocalDate fechaDeNacimiento, CuestionarioRespondido cuestionarioRespondido, String razonSocial, String rubro, TipoPersona tipoPersona, Contacto contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeComunicacion = new ArrayList<>();
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.formasDeColaboracion = new ArrayList<>();
        this.cuestionarioRespondido = cuestionarioRespondido;
        this.razonSocial = razonSocial;
        this.tipoPersona = tipoPersona;
        this.contacto = new ArrayList<>();
    }

    public void cargarRespuestas(CuestionarioRespondido cuestionarioRespondido) {
        for (int i = 0; i < cuestionarioRespondido.getRespuestas().size(); i++) {
            String respuesta = cuestionarioRespondido.getRespuestas().get(i).getRespuestaAbierta();
            Class<Colaborador> claseColab = Colaborador.class;
            try {
                Field campo = claseColab.getDeclaredField(cuestionarioRespondido.getRespuestas().get(i).getPregunta().getNombre());
                if (cuestionarioRespondido.getRespuestas().get(i).getPregunta().getTipoPregunta().equals("FECHA")) {
                    campo.set(this, StringToFecha(respuesta));
                    continue;
                }
                campo.set(this, respuesta);
            } catch (NoSuchFieldException | ParseException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Object StringToFecha(String fecha) throws ParseException {
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

    public Double puntosActualesDisponibles() {
        return CalculadorPuntos.getInstancia().sumarPuntosA(this) - this.puntosTotalesUsados;
    }

    public void agregarOfertasCanjeadas(Oferta oferta) {
        this.ofertasRegistradas.add(oferta);
        RepoColaborador.INSTANCE.modificar(this);
    }

    public List<Coordenada> obtenerPuntosRecomendadosParaHeladera(Double longitud, Double latitud, Integer radio) throws IOException {
        return iRecomendacionPuntos.recomendarPuntos(longitud, latitud, radio);
    }

    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println("Soy " + this.nombre + " " + this.apellido + " y recibí el mensaje: " + mensaje);
    }
}