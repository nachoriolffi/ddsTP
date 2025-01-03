package ar.edu.utn.frba.dds.models.entities.contacto.telegram;

import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioComunicacion;

public class Telegram extends Mensaje implements MedioComunicacion {

    private IAdapterTelegram adapterTelegram;

    public Telegram(IAdapterTelegram adapterTelegram) {
        this.adapterTelegram = adapterTelegram;
    }

    public Telegram(){
    }


    @Override
    public void comunicar(Notificacion notificacion) {
        adapterTelegram.comunicarMensaje(notificacion.getMensaje(), notificacion.filtrarContacto(TipoContacto.TELEGRAM));
    }
}
