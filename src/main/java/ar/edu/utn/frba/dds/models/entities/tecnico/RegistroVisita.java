package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registroVisita")
@Getter
@Setter
public class RegistroVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaVisita", nullable = false)
    private Date fechaVisita;

    @ManyToOne
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "pathFoto", columnDefinition = "VARCHAR (255)")
    private String pathFoto;

    @ManyToOne
    @JoinColumn(name = "incidente_id", nullable = false)
    private Incidente incidenteASolucionar;

    @Column(name = "problemaSolucionado", nullable = false)
    private Boolean problemaSolucionado;

}
