package ar.edu.utn.frba.dds.contacto;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public interface MedioDeComunicacion{
    public void comunicar (Mensaje mensaje, Colaborador destinatario);
}
