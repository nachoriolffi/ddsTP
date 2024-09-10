package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    public Integer getCantidadViandas() {
        return null;
    }
}
