package ar.edu.utn.frba.dds.models.repositories.implementaciones;


import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;


public class RepoContacto extends RepoGenerico<Contacto>{


    public static RepoContacto INSTANCE = new RepoContacto();

    public RepoContacto() {super(Contacto.class);}


}
