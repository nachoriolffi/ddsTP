package ar.edu.utn.frba.dds.contacto.correo;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.contacto.Mensaje;

public interface MedioDeComunicacion{
    public void comunicar (Mensaje mensaje, Colaborador destinatario);
}
