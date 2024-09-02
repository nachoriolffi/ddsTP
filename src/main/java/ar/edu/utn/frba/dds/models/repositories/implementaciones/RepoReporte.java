package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Reporte;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoReporte extends RepoGenerico<Reporte> {

    public static RepoReporte INSTANCE = new RepoReporte();

    public RepoReporte() {
        super(Reporte.class);
    }



}
