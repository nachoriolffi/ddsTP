package ar.edu.utn.frba.dds.contacto;

import ar.edu.utn.frba.dds.colaborador.Colaborador;

public class CorreoElectronico extends Mensaje implements MedioDeComunicacion{

private IAdapterCorreo aCorreo;

    public CorreoElectronico() {
        this.aCorreo = new AdapterCorreo();
    }
    public CorreoElectronico(IAdapterCorreo aCorreo) {
        this.aCorreo = aCorreo;
    }

    @Override
    public void comunicar(Mensaje mensaje, Colaborador destinatario) {

    }

}
