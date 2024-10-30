package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public class AdapterCorreo implements IAdapterCorreo {

    private ServicioMail servicioMail = ServicioMail.getInstance();

    public AdapterCorreo(ServicioMail servicioMail) {
        this.servicioMail = servicioMail;
    }

    public AdapterCorreo() {
    }



    @Override
    public void comunicarMensaje(Mensaje mensaje, Contacto destinatario) {
        // se envia el destinatario, el titulo del mensaje y el mensaje como tal
        servicioMail.enviarCorreo(destinatario.getDescripcion(), mensaje.getTitulo(), mensaje.getMensaje());
    }
}
