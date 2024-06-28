package ar.edu.utn.frba.dds.models.entities.contacto.telegram;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public class AdapterTelegram implements IAdapterTelegram {
    private ServicioTelegram servicioTelegram = ServicioTelegram.getInstance();;

    public void AdapterTelegram(ServicioTelegram servicioTelegram) {
        this.servicioTelegram = servicioTelegram;
    }

    public void AdapterTelegram(){
    }

    @Override
    public void comunicarMensaje(Mensaje mensaje, Contacto destinatario) {
        servicioTelegram.enviarTelegram(destinatario.getDescripcion(), mensaje.getMensaje());
    }
}
