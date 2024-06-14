package ar.edu.utn.frba.dds.exportadorPDF.adapterPDF;

import ar.edu.utn.frba.dds.exportadorPDF.Exportable;

public interface InterfaceAdapterPDF {

    private void Exportar(Exportable exportable){
        System.out.println("Exportando a PDF");
    }

}
