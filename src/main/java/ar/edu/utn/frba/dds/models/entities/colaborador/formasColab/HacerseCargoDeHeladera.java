package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera extends FormaDeColaboracion {

    //@Id@GeneratedValue
    //private Long id;
    //@ManyToOne
    //@JoinColumn(name = "id",referencedColumnName = "id_Local",nullable = false)
    @Transient
    private Local local;
    @OneToOne
    @JoinColumn (name = "id",referencedColumnName = "id_Heladera",nullable = false)
    private Heladera heladera;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoColaboracion tipoColaboracion;
    @Column(name = "fechaColaboracion", columnDefinition = "DATE", nullable = false)
    private Date fechaColaboracion;


    public HacerseCargoDeHeladera() {

    }

    public HacerseCargoDeHeladera(Heladera heladera, TipoColaboracion tipoColaboracion) {
        this.heladera = heladera;
        this.tipoColaboracion = tipoColaboracion;
        this.fechaColaboracion = new Date();
    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return this.sumarMesesActivas() * ConfiguracionMultiplicador.getInstance().getMultiplicadorHeladeraActiva();
    }


    @Override
    public Integer getCantidadViandas() {
        return null;
    }

    public long sumarMesesActivas() {

        long mesesActivas = 0;
        mesesActivas += heladera.mesesActiva(fechaColaboracion);
        return mesesActivas;

    }

}
