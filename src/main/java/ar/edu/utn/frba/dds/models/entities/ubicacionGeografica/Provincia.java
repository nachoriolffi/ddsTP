package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;



public class Provincia {

    private String provincia;
    private List<Municipio> municipios;
}
