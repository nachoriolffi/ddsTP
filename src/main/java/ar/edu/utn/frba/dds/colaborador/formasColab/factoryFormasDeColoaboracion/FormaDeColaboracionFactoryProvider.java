package ar.edu.utn.frba.dds.colaborador.formasColab.factoryFormasDeColoaboracion;

import ar.edu.utn.frba.dds.colaborador.formasColab.TipoColaboracion;

import java.util.HashMap;
import java.util.Map;

public class FormaDeColaboracionFactoryProvider {
    private final Map<TipoColaboracion, FormaDeColaboracionFactory> factories = new HashMap<TipoColaboracion, FormaDeColaboracionFactory>();

    public FormaDeColaboracionFactoryProvider() {
        factories.put(TipoColaboracion.DINERO, new DonacionDineroFactory());
        factories.put(TipoColaboracion.DONACION_VIANDAS, new DonacionViandaFactory());
        factories.put(TipoColaboracion.REDISTRIBUCION_VIANDAS, new DistribucionViandaFactory());
        factories.put(TipoColaboracion.ENTREGA_TARJETAS, new RegistroVulnerableFactory());
    }

    public FormaDeColaboracionFactory getFactory(TipoColaboracion tipoColaboracion) {
        return factories.get(tipoColaboracion);
    }
}
