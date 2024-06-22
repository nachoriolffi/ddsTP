package ar.edu.utn.frba.dds.models.entities.contacto.telegram;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;

public class Telegram extends Mensaje implements MedioDeComunicacion {

    private IAdapterTelegram adapterTelegram;

    public Telegram(AdapterTelegram adapterTelegram) {
        this.adapterTelegram = adapterTelegram;
    }

    public Telegram(){
    }


    @Override
    public void comunicar(Mensaje mensaje, Colaborador destinatario) {
        adapterTelegram.comunicarMensaje(mensaje, destinatario);
    }
}
