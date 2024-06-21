package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public interface IAdapterCorreo {
    public void comunicarMensaje(Mensaje mensaje, Colaborador destinatario);

}
