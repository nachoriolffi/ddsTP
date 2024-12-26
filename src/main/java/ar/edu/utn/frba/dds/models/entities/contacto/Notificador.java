package ar.edu.utn.frba.dds.models.entities.contacto;

import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioComunicacion;

public class Notificador {

    public void notificar(MedioComunicacion medioComunicacion, Mensaje mensaje) {
        System.out.println(mensaje);
    }
}
