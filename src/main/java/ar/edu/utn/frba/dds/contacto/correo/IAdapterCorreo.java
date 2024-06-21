package ar.edu.utn.frba.dds.contacto.correo;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Mensaje;

public interface IAdapterCorreo {
    public void comunicarMensaje(Mensaje mensaje, Colaborador destinatario);

}
