package ar.edu.utn.frba.dds.models.entities.contacto.telegram;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public interface IAdapterTelegram {

    public void comunicarMensaje(Mensaje mensaje, Contacto destinatario);


}
