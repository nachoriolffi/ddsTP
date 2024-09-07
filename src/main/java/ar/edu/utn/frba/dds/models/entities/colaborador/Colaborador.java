package ar.edu.utn.frba.dds.models.entities.colaborador;

import ar.edu.utn.frba.dds.models.converters.MedioComunicacionAtributeConvertere;
import ar.edu.utn.frba.dds.models.entities.colaborador.calculoPuntos.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.IRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.recomendacionPuntos.AServicioRecomendacionPuntos;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;

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
public class Colaborador {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11)")
>>>>>>> ed7271b05ddaca1f501ec0b9e5b94f6fe5191aa9
    private Integer id_Colaborador;
    @Column(name="nombre", columnDefinition = "VARCHAR(25)")// es nullable porque el colaborador puede ser una persona jurídica
    private String nombre;
<<<<<<< HEAD
    @Column(name = "apellido", columnDefinition = "VARCHAR(25)")
    private String apellido;
    @Convert(converter = MedioComunicacionAtributeConvertere.class)
    @ElementCollection(targetClass = String.class)
    private List<MedioDeComunicacion> mediosDeComunicacion;
    @Column(name = "fechaDeNacimiento", columnDefinition = "DATE")
=======
    @Column(name="apellido", columnDefinition = "VARCHAR(25)")// idem anterior
    private String apellido;
    @Convert( converter = MedioComunicacionAtributeConvertere.class)
    @ElementCollection ( targetClass = String.class)
    //@JoinColumn(name = "mediosDeComunicacion", nullable = false) -> chequear, creo que es así para que no sea nullable. Tiene que quedar como no nullable acá
    private List<MedioDeComunicacion> mediosDeComunicacion;
    @Column(name="fechaDeNacimiento", columnDefinition = "DATE") // el enunciado dice que la fecha de nacimiento  es opcional, por eso es nullable
>>>>>>> ed7271b05ddaca1f501ec0b9e5b94f6fe5191aa9
    private LocalDate fechaDeNacimiento;
    @ManyToOne
    @JoinColumn(name = "direccion_id") // el enunciado dice que la direccion  es opcional, por eso es nullable
    private Direccion direccion;
    @OneToMany
    @JoinColumn(name = "id_FormasColaboracion", referencedColumnName = "if")
    private List<FormaDeColaboracion> formasDeColaboracion;
<<<<<<< HEAD
    @OneToMany
    @JoinColumn(name = "id_ColaboracionesHechas", referencedColumnName = "id")
=======
    @Transient // revisar
    @Column(name = "nombre", columnDefinition = "VARCHAR(25)")
>>>>>>> ed7271b05ddaca1f501ec0b9e5b94f6fe5191aa9
    private List<FormaDeColaboracion> colaboracionesRealizadas;
    @ManyToOne
    @JoinColumn(name = "cuestionario_id")
    private CuestionarioRespondido cuestionarioRespondido;
<<<<<<< HEAD
    @Column(name = "razonSocial", columnDefinition = "VARCHAR(255)")
=======
    @Column(name = "razonSocial", columnDefinition = "INT(11)")
>>>>>>> ed7271b05ddaca1f501ec0b9e5b94f6fe5191aa9
    private String razonSocial;
    @Enumerated(EnumType.STRING)
    private TipoJuridisccion tipoJuridisccion;
    @OneToOne
    @JoinColumn(name = "rubro_id")
    private RubroColaborador rubroColaborador;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;
    @OneToMany
<<<<<<< HEAD
    @JoinColumn(name = "id_contacto")
=======
    @JoinColumn (name = "colaborador_id")
>>>>>>> ed7271b05ddaca1f501ec0b9e5b94f6fe5191aa9
    private List<Contacto> contacto;
    @Transient
    private IRecomendacionPuntos iRecomendacionPuntos;
    @Column(name = "puntosTotalesUsados", columnDefinition = "DOUBLE")
    private Double puntosTotalesUsados;
    @Column(name = "numeroDocumento", columnDefinition = "INT(11)")
    private Integer numeroDocumento; //nuevo requerimiento para carga masiva
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento; //nuevo requerimiento para carga masiva
    @ManyToMany
    @JoinColumn(name = "id_ofertasRegistradas")
    private List<Oferta> ofertasRegistradas;

    // CONSTRUCTORES

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

    public Colaborador(String nombre, String apellido, List<MedioDeComunicacion> mediosDeComunicacion, List<FormaDeColaboracion> formasDeColaboracion, CuestionarioRespondido cuestionarioRespondido, TipoPersona tipoPersona) {
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

    public void agregarOfertasCanjeadas(List<Oferta> ofertas) {
        this.ofertasRegistradas.addAll(ofertas);
    }

    public List<Coordenada> obtenerPuntosRecomendadosParaHeladera(Double longitud, Double latitud, Integer radio) throws IOException {
        return iRecomendacionPuntos.recomendarPuntos(longitud, latitud, radio);
    }
}
