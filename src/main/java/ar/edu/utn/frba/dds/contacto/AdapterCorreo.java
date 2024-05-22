package ar.edu.utn.frba.dds.contacto;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public class AdapterCorreo implements IAdapterCorreo{

    public AdapterCorreo( ServicioMail servicioMail) {
        this.servicioMail = servicioMail;
    }

    public AdapterCorreo() {
        ;
    }

    private ServicioMail servicioMail= ServicioMail.getInstance();

    public void comunicarMensaje(Mensaje mensaje, Colaborador destinatario){
        servicioMail.enviarCorreo(destinatario.getContacto().getDescripcion(), mensaje.getTitulo(), mensaje.getMensaje());
    }
}
