package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera extends FormaDeColaboracion {

    @Transient
    private Local local;

    @OneToOne
    @JoinColumn(name = "id_heladera", nullable = false)
    private Heladera heladera;

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

    public long sumarMesesActivas() {
        long mesesActivas = 0;
        mesesActivas += heladera.mesesActiva(fechaColaboracion);
        return mesesActivas;
    }

}
