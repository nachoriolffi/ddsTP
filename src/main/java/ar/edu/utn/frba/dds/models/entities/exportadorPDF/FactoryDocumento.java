package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;

public class FactoryDocumento {
    public Exportable crearDocumento(TipoDocumento tipo) {
        switch (tipo) {
            case FALLAS_HELADERA:
                return new DocumentoFallasHeladera();
            case VIANDAS_DONADAS:
                return new DocumentoViandasDonadas();
            case VIANDAS_IN_OUT:
                return new DocumentoViandasInOut();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado: " + tipo);
        }
    }
}
