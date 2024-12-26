package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.converters.MedioComunicacionConverter;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioComunicacion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tecnico")
@Getter
@Setter
public class Tecnico {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "cuil", unique = true)
    private String cuil;

    @ElementCollection
    @CollectionTable(name = "medioComunicacion")
    @Convert(converter = MedioComunicacionConverter.class)
    @Column(name = "medio")
    private List<MedioComunicacion> mediosComunicacion;

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

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public void registrarVisita(RegistroIncidente registro) {
        RepoRegistrosVisita.INSTANCE.agregar(registro);
    }

    public void agregarMedioDeComunicacion(MedioComunicacion medioComunicacion) {
        this.mediosComunicacion.add(medioComunicacion);
    }

    public void agregarContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

}
