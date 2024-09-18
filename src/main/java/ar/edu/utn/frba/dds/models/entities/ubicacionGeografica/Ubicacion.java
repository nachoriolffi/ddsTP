package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class Ubicacion {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    public Ubicacion(Provincia provincia, Municipio municipio, Localidad localidad) {
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
    }

    public Ubicacion() {
    }
}