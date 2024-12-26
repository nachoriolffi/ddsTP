package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;

public class CorreoElectronico extends Mensaje implements MedioComunicacion {

    private IAdapterCorreo aCorreo = new AdapterCorreo();

    public CorreoElectronico() {
    }

    public CorreoElectronico(AdapterCorreo aCorreo) {
        this.aCorreo = aCorreo;
    }

    @Override
    public void comunicar(Notificacion notificacion) {
        Mensaje mensaje = notificacion.getMensaje();
        Contacto contacto = notificacion.filtrarContacto(TipoContacto.MAIL);
        aCorreo.comunicarMensaje(mensaje, contacto);
    }

}
