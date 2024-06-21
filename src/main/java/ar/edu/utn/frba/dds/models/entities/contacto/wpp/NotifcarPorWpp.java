package ar.edu.utn.frba.dds.models.entities.contacto.wpp;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;

public class NotifcarPorWpp implements MedioDeComunicacion {

    private IAdapterWpp aWpp;

    public NotifcarPorWpp(IAdapterWpp aWpp) {
        this.aWpp = aWpp;
    }

    public NotifcarPorWpp() {
    }

    @Override
    public void comunicar(Mensaje mensaje, Colaborador destinatario) {

        aWpp.comunicarMensaje(mensaje.getMensaje(), destinatario.getContacto().getDescripcion());

    }
}
