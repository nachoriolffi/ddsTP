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

import java.util.Date;

@Getter
@Setter
public class Incidente {
    Long id;
    private Date fecha;
    private String descripcion;
    private String pathFoto;
    private TipoIncidente tipoIncidente;
    private TipoAlerta tipoAlerta;
    private Colaborador colaborador;

    public Incidente(TipoAlerta tipoAlerta) {
        this.fecha = new Date();
        this.tipoAlerta = tipoAlerta;
    }

    public Incidente() {
        this.fecha = new Date();
    }

    public Incidente(Long id, String descripcion, String pathFoto, TipoIncidente tipoIncidente, TipoAlerta tipoAlerta, Colaborador colaborador) {
        this.id = id;
        this.fecha = new Date();
        this.descripcion = descripcion;
        this.pathFoto = pathFoto;
        this.tipoIncidente = tipoIncidente;
        this.tipoAlerta = tipoAlerta;
        this.colaborador = colaborador;
    }

    public void notificarTecnicoMasCercano(Heladera heladera){
     CalculadorDistanciasTecnicoHeladera calculador = CalculadorDistanciasTecnicoHeladera.getInstance();
    Tecnico tecnicoMasCercano = calculador.calcularTecnicoMasCercano(RepoTecnico.getInstancia().getTecnicos(),heladera);
    Notificacion notificaion = new Notificacion(tecnicoMasCercano.getContactos(), new Mensaje("Alerta de incidente", "Se ha detectado un incidente en la heladera"));
    tecnicoMasCercano.getMediosDeComunicacion().forEach(medioDeComunicacion -> medioDeComunicacion.comunicar(notificaion));
    }

}



