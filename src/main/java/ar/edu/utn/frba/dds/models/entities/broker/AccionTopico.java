package ar.edu.utn.frba.dds.models.entities.broker;

import java.io.IOException;

public interface AccionTopico {

    public void evaluarMensaje(String id,String messageContent) throws IOException;

}
