package ar.edu.utn.frba.dds.models.entities.vulnerable;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vulnerable")
public class Vulnerable {

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

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;

    @Column(name = "situacionCalle")
    private Boolean situacionCalle;

    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numeroDocumento")
    private String numeroDocumento;

    @OneToMany
    @JoinColumn(name = "vulnerable_id")
    private List<MenorACargo> menoresACargo;

    public Vulnerable() {
    }

    public void agregarMenor(MenorACargo menor) {
        this.menoresACargo.add(menor);
    }

}
