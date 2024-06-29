package ar.edu.utn.frba.dds.models.entities.contacto;

import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;

public class Notificador {

    public void notificar(MedioDeComunicacion medioDeComunicacion, Mensaje mensaje) {
        System.out.println(mensaje);
    }
}
