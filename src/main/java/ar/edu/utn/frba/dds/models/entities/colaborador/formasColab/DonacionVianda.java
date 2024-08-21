package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;



public class DonacionVianda implements FormaDeColaboracion {

    private List<Vianda> viandas;
    @Getter
    @Setter
    private Integer cantidadViandas;
    private Date fechaColaboracion;
    @Getter
    @Setter
    private TipoColaboracion tipoColaboracion;

    public DonacionVianda(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = cantidad;

    }


    public DonacionVianda(List<Vianda> viandas, Date fechaColaboracion) {
        this.viandas = viandas;
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadViandas = viandas.size();
        this.tipoColaboracion  = TipoColaboracion.DONACION_VIANDAS;
    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadViandas * ConfiguracionMultiplicador.getInstance().getMultiplicadorViandasDonadas();
    }

    public void solicitarAutorizacion(Heladera heladera, Tarjeta tarjeta){
        Broker broker = Broker.getInstance();
        broker.publish("heladeras/"+ heladera.getNombre() + "/autorizacion", tarjeta.getIdTarjeta().toString());
    }

}
