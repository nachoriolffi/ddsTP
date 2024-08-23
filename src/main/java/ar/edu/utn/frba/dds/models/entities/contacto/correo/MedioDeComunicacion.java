package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;
import ar.edu.utn.frba.dds.models.entities.contacto.Notificacion;

public interface MedioDeComunicacion{

    public void comunicar (Notificacion notificacion);
}
