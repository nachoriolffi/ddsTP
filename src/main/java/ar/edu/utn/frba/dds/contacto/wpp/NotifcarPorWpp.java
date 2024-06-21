package ar.edu.utn.frba.dds.contacto.wpp;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Mensaje;
import ar.edu.utn.frba.dds.contacto.correo.MedioDeComunicacion;

public class NotifcarPorWpp implements MedioDeComunicacion {

    private IAdapterWpp aWpp;

    public NotifcarPorWpp(IAdapterWpp aWpp) {
        this.aWpp = aWpp;
    }

    public NotifcarPorWpp() {
    }

    @Override
    public void comunicar(Mensaje mensaje, Colaborador destinatario) {

    }
}
