package ar.edu.utn.frba.dds.models.entities.vulnerable;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "registro_menor_a_cargo")
@Setter
public class MenorACargo {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numeroDocumento")
    private String numeroDocumento;

    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    public MenorACargo() {
    }
}
