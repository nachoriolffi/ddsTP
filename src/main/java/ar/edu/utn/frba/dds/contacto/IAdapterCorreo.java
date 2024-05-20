package ar.edu.utn.frba.dds.contacto;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public interface IAdapterCorreo {
    public void comunicarMensaje(Mensaje mensaje, Colaborador destinatario);

}
