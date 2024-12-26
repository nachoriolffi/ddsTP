package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "registroVisita")
@Getter
@Setter
public class RegistroIncidente {

    @Id
    @GeneratedValue
    private Long id;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaVisita")
    private LocalDate fechaVisita;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "pathFoto")
    private String pathFoto;

    @ManyToOne
    @JoinColumn(name = "incidente_id")
    private Incidente incidente;

    @Column(name = "fueSolucionado")
    private Boolean fueSolucionado;

}
