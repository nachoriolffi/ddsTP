package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.*;


public class Ubicacion {



    private Long id_Ubicacion;

    private Provincia provincia;

    private Municipio municipio;

    private Localidad localidad;
    //@ManyToOne
    //private Coordenada coordenada;

    public Ubicacion(Provincia provincia, Municipio municipio, Localidad localidad) {
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;

    }
}
