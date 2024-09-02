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
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_RegistroVisita;
    @Column(name = "fechaVisita",columnDefinition = "Date",nullable = false)
    private Date fechaVisita;
    @ManyToOne
    @JoinColumn(name = "id_Tecnico",nullable = false)
    private Tecnico tecnico;
    @Column(name="descripcion",columnDefinition = "TEXT",nullable = false)
    private String descripcion;
    @Column(name="pathFoto",columnDefinition = "VARCHAR (250)")
    private String pathFoto;
    @ManyToOne
    @JoinColumn(name = "id_Incidente",nullable = false)
    private Incidente incidenteASolucionar;
    @Column(name = "problemaSolucionado",columnDefinition = "Boolean",nullable = false)
    private Boolean problemaSolucionado;

}
