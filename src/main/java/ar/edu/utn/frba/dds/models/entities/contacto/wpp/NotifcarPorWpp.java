package ar.edu.utn.frba.dds.models.entities.contacto.wpp;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;

import javax.print.attribute.standard.Destination;

public class NotifcarPorWpp implements MedioDeComunicacion {

    private IAdapterWpp aWpp;

    public NotifcarPorWpp(IAdapterWpp aWpp) {
        this.aWpp = aWpp;
    }

    public NotifcarPorWpp() {
    }

    @Override
    public void comunicar(Notificacion notificacion) {

        aWpp.comunicarMensaje(notificacion.getMensaje().getMensaje(), notificacion.filtrarContacto(TipoContacto.WPP).getDescripcion());

    }
}
