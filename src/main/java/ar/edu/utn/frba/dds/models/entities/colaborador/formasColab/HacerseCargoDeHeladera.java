package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Local;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;



public class HacerseCargoDeHeladera implements FormaDeColaboracion {
    private Local local;
    private List<Heladera> heladeras;
    private Integer cantidadHeladeras;
    @Getter
    @Setter
    private TipoColaboracion tipoColaboracion;
    private Date fechaColaboracion;



    public HacerseCargoDeHeladera(List<Heladera> heladeras, TipoColaboracion tipoColaboracion) {
        this.heladeras = heladeras;
        this.tipoColaboracion = tipoColaboracion;
        this.fechaColaboracion = new Date();
        this.cantidadHeladeras = heladeras.size();
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
