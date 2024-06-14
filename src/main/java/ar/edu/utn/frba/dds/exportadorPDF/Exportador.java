package ar.edu.utn.frba.dds.exportadorPDF;

public class Exportador {

    private Reporte exportar(Exportable exportable, EstrategiaExportacion estrategia){

        return estrategia.exportar(exportable);

    }

}


