package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class  FormaDeColaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public double sumarPuntosA(Colaborador colaborador){
        return 0;
    }
    public TipoColaboracion getTipoColaboracion(){
        return null;
    }
    public Integer getCantidadViandas(){
        return null;
    }
}
