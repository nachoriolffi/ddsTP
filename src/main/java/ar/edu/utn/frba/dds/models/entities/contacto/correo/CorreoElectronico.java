package ar.edu.utn.frba.dds.models.entities.contacto.correo;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Mensaje;

public class CorreoElectronico extends Mensaje implements MedioDeComunicacion{

    private IAdapterCorreo aCorreo;

    public CorreoElectronico() {
    }

    public CorreoElectronico(AdapterCorreo aCorreo) {
        this.aCorreo = aCorreo;
    }

    @Override
    public void comunicar(Mensaje mensaje, Colaborador destinatario) {
        aCorreo.comunicarMensaje(mensaje, destinatario);
    }

}
