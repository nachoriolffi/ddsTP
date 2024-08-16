package ar.edu.utn.frba.dds.models.entities.broker;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

public class ColaboradorAccion implements AccionTopico{
    @Override
    public void evaluarMensaje(String id, String messageContent) {
        //en el topico se publica solo el id de la tarjeta??
        System.out.println("ID de la tarjeta del Colaborador: " + messageContent);

        Heladera heladera = RepoHeladeras.getInstancia().buscarHeladeraPorNombre(id);
        heladera.agregarRegistroSolicitud(RegistroSolicitud messageContent);

    }
}
