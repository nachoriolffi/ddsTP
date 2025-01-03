package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "forma_de_colaboracion") //pongo esto para que quede mejor
public abstract class FormaDeColaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    Colaborador colaborador;

    public double sumarPuntosA(Colaborador colaborador) {
        return 0;
    }

    public TipoColaboracion getTipoColaboracion() {
        return null;
    }

    //public Integer getCantidadViandas() {
    //   return null;
    //}
}
