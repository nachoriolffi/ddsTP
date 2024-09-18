package ar.edu.utn.frba.dds.models.entities.heladera.alerta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha",nullable = false)
    private Date fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "pathFoto", columnDefinition = "TEXT")
    private String pathFoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoIncidente",nullable = false)
    private TipoIncidente tipoIncidente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoAlerta")
    private TipoAlerta tipoAlerta;

    @OneToOne
    @JoinColumn(name = "colaborador_id",nullable = false)
    private Colaborador colaborador;

    public Incidente(TipoAlerta tipoAlerta) {
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }

    public Incidente() {
        this.fecha = new Date();
    }

    public Incidente(String descripcion, String pathFoto, TipoIncidente tipoIncidente, TipoAlerta tipoAlerta, Colaborador colaborador) {
        this.fecha = new Date();
        this.descripcion = descripcion;
        this.pathFoto = pathFoto;
        this.tipoIncidente = tipoIncidente;
        this.tipoAlerta = tipoAlerta;
        this.colaborador = colaborador;
    }

    public void notificarTecnicoMasCercano(Heladera heladera){
        CalculadorDistanciasTecnicoHeladera calculador = CalculadorDistanciasTecnicoHeladera.getInstance();
        Tecnico tecnicoMasCercano = calculador.calcularTecnicoMasCercano(RepoTecnico.INSTANCE.buscarTodos(), heladera);
        Notificacion notificaion = new Notificacion(tecnicoMasCercano.getContactos(), new Mensaje("Alerta de incidente", "Se ha detectado un incidente en la heladera"));
        tecnicoMasCercano.getMediosDeComunicacion().forEach(medioDeComunicacion -> medioDeComunicacion.comunicar(notificaion));
    }

}



