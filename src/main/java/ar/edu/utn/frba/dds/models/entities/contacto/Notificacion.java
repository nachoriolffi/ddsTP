package ar.edu.utn.frba.dds.models.entities.contacto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Notificacion {
    private List<Contacto> contactos;
    private Mensaje mensaje;

    public Contacto filtrarContacto(TipoContacto tipoContacto){
        return this.contactos.stream().filter(contacto -> contacto.getTipoContacto().equals(tipoContacto)).findFirst().orElse(null);
    }

    public Notificacion(List<Contacto> contacto, Mensaje mensaje) {
        this.contactos = contacto;
        this.mensaje = mensaje;
    }
}
