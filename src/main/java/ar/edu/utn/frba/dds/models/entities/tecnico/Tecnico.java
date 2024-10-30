package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.converters.MedioComunicacionAtributeConvertere;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tecnico")
@Getter
@Setter
public class Tecnico {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)", nullable = false)
    private String nombre;
    @Column(name = "apellido", columnDefinition = "VARCHAR(255)", nullable = false)
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "cuil", nullable = false)
    private Integer cuil; // cambiar por string luego

    @Convert(converter = MedioComunicacionAtributeConvertere.class)
    @ElementCollection(targetClass = String.class)
    private List<MedioDeComunicacion> mediosDeComunicacion;

    @Column(name = "areaCobertura", nullable = false)
    private Integer areaCobertura;

    @OneToOne
    @JoinColumn(name = "coordenada_id")
    private Coordenada coordenada;

    @Column(name = "disponible")
    private Boolean disponible;

    @OneToMany
    @JoinColumn(name = "tecnico_id")
    private List<Contacto> contactos;

    public Tecnico() {
        this.contactos = new ArrayList<>();
        this.mediosDeComunicacion = new ArrayList<>();
    }

    public Tecnico(Long id, String nombre, String apellido, TipoDocumento tipoDocumento, Integer dni, Integer cuil, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.dni = dni;
        this.cuil = cuil;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
    }

    public Tecnico(String nombre, String apellido, Coordenada coordenada, Boolean disponible, Integer areaCobertura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.coordenada = coordenada;
        this.disponible = disponible;
        this.areaCobertura = areaCobertura;
    }

    public Tecnico(Long id, String nombre, String apellido, TipoDocumento tipoDocumento, Integer dni, Integer cuil, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura, Coordenada coordenada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.dni = dni;
        this.cuil = cuil;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
        this.coordenada = coordenada;
    }

    public void registrarVisita(RegistroVisita registro) {
        RepoRegistrosVisita.INSTANCE.agregar(registro);
    }

    public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
        this.mediosDeComunicacion.add(medioDeComunicacion);
    }

    public void agregarContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

}
