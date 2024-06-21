package ar.edu.utn.frba.dds.contacto.correo;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Mensaje;

public class AdapterCorreo implements IAdapterCorreo {

    public AdapterCorreo(ServicioMail servicioMail) {
        this.servicioMail = servicioMail;
    }

    public AdapterCorreo() {
    }

    private ServicioMail servicioMail = ServicioMail.getInstance();

    @Override
    public void comunicarMensaje(Mensaje mensaje, Colaborador destinatario) {
        // se envia el destinatario, el titulo del mensaje y el mensaje como tal
        servicioMail.enviarCorreo(destinatario.getContacto().getDescripcion(), mensaje.getTitulo(), mensaje.getMensaje());
    }
}
