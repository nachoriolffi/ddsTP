package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public interface IAdapterCorreo {
    public void comunicarMensaje(Mensaje mensaje, Contacto destinatario);

}
