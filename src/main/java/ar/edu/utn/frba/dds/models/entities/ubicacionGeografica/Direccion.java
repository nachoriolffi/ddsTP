package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import lombok.Setter;

public class Direccion {
    @Setter
    private Calle calle;
    private Integer altura;
    private Integer piso;
    private Ubicacion ubicacion;

    public Direccion(String calle, Integer altura, Integer piso) {
        this.calle = new Calle(calle);
    }
    public Direccion(String calle, Integer altura, Integer piso, Ubicacion ubicacion) {
        this.calle = new Calle(calle);
        this.altura = altura;
        this.piso = piso;
        this.ubicacion = ubicacion;
    }


}
