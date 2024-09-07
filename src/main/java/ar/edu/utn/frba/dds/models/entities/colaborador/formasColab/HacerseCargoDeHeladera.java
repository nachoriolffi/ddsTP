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
@Table(name="hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera implements FormaDeColaboracion {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Local local; // TODO LOCAL
    //@OneToMany
    //@JoinColumn (name = "id_heladera")
    @Transient
    private List<Heladera> heladeras;
    @Column(name="cantidadHeladeras", columnDefinition = "INT")
    private Integer cantidadHeladeras;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion;
    @Column(name="fechaColaboracion", columnDefinition = "DATE")
    private Date fechaColaboracion;



    public HacerseCargoDeHeladera(List<Heladera> heladeras, TipoColaboracion tipoColaboracion) {
        this.heladeras = heladeras;
        this.tipoColaboracion = tipoColaboracion;
        this.fechaColaboracion = new Date();
        this.cantidadHeladeras = heladeras.size();
    }

    public HacerseCargoDeHeladera() {

    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadHeladeras * this.sumarMesesActivas() * ConfiguracionMultiplicador.getInstance().getMultiplicadorHeladeraActiva();
    }


    @Override
    public Integer getCantidadViandas() {
        return null;
    }

    public long sumarMesesActivas() {

        long mesesActivas = 0;

        for (Heladera heladera : heladeras) {
            mesesActivas += heladera.mesesActiva(fechaColaboracion);
        }
        return mesesActivas;

    }

}
