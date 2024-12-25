package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;

public class RepoDireccion  extends RepoGenerico<Direccion>{
    public static RepoDireccion INSTANCE = new RepoDireccion();
    public RepoDireccion() {
        super(Direccion.class);
    }
}