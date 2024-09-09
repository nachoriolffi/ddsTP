package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Tecnico")
@Getter @Setter
public class Tecnico {


    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id_Tecnico;

    @Column (name = "nombre", columnDefinition = "VARCHAR(250)")
    private String nombre;
    @Column (name = "apellido", columnDefinition = "VARCHAR(250)")
    private String apellido;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column (name = "DNI", columnDefinition = "INT")
    private Integer DNI;

    @Column (name = "CUIL", columnDefinition = "INT")
    private Integer CUIL; // cambiar por string luego

    @Transient
    private List<MedioDeComunicacion> mediosDeComunicacion;
    @Column (name = "areaCobertura", columnDefinition = "INT")
    private Integer areaCobertura;

    @OneToOne
    @JoinColumn(name = "id_Coordenada",nullable = false)
    private Coordenada coordenada;

    @Column (name = "disponible", columnDefinition = "BOOLEAN")
    private Boolean disponible;

    @OneToMany
    @JoinColumn(name = "id_Contacto", referencedColumnName = "id_Tecnico")
    private List<Contacto> contactos;

    public Tecnico(){

    }

    public Tecnico(Long id_Tecnico, String nombre, String apellido, TipoDocumento tipoDocumento, Integer DNI, Integer CUIL, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura ) {
        this.id_Tecnico = id_Tecnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.DNI = DNI;
        this.CUIL = CUIL;
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

    public Tecnico(Long id_Tecnico, String nombre, String apellido, TipoDocumento tipoDocumento, Integer DNI, Integer CUIL, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura, Coordenada coordenada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.DNI = DNI;
        this.CUIL = CUIL;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
        this.coordenada = coordenada;
    }

    public void registrarVisita(RegistroVisita registro){
        RepoRegistrosVisita.INSTANCE.agregar(registro);
    }

}
