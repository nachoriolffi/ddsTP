package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registroVisita")
public class RegistroVisita {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_RegistroVisita;
    @Column(name = "fechaVisita",columnDefinition = "Date")
    private Date fechaVisita;
    @ManyToOne
    @JoinColumn(name = "id_Tecnico")
    private Tecnico tecnico;
    @Column(name="descripcion",columnDefinition = "TEXT")
    private String descripcion;
    @Column(name="pathFoto",columnDefinition = "VARCHAR (250)")
    private String pathFoto;
    @ManyToOne
    @JoinColumn(name = "id_Incidente")
    private Incidente incidenteASolucionar;
    @Column(name = "problemaSolucionado",columnDefinition = "Boolean")
    private Boolean problemaSolucionado;

}
