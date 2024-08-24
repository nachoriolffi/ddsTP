package ar.edu.utn.frba.dds.models.entities.heladera;

import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;

import javax.persistence.*;

@Entity
@Table(name = "registro_solicitud_vianda")
public class RegistroSolicitudVianda {

    @Id
    @GeneratedValue ( strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id_RegistroSolicitudVianda;

    @ManyToOne
    @JoinColumn ( name = "id_vianda")
    private Vianda vianda;

    @ManyToOne
    @JoinColumn ( name = "id_registro_solicitud")
    private RegistroSolicitud  registroSolicitud;



}
