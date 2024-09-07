package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class  FormaDeColaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
