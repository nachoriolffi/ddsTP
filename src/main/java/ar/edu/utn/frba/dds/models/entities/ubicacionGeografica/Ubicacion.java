package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class Ubicacion {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Provincia", referencedColumnName = "id_Provincia")
    private Provincia provincia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Municipio", referencedColumnName = "id_Municipio")
    private Municipio municipio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Localidad", referencedColumnName = "id_Localidad")
    private Localidad localidad;

    public Ubicacion(Provincia provincia, Municipio municipio, Localidad localidad) {
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
    }

    public Ubicacion() {
    }
}