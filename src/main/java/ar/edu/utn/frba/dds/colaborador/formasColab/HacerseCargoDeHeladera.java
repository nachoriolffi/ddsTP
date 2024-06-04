package ar.edu.utn.frba.dds.colaborador.formasColab;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.reconocimiento.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.ubicacionGeografica.Local;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class HacerseCargoDeHeladera implements FormaDeColaboracion {
    private Local local;
    private List<Heladera> heladeras;
    private Integer cantidadHeladeras;
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

    public long sumarMesesActivas() {

        long mesesActivas = 0;

        for (Heladera heladera : heladeras) {
            mesesActivas += heladera.mesesActiva(fechaColaboracion);
        }
        return mesesActivas;

    }

}