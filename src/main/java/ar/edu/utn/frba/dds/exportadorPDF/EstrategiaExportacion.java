package ar.edu.utn.frba.dds.exportadorPDF;

public interface EstrategiaExportacion {
    default Reporte exportar(Exportable exportable){
        return new Reporte();
    };
}
