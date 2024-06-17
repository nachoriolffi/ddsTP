package ar.edu.utn.frba.dds.tecnico;

import ar.edu.utn.frba.dds.contacto.MedioDeComunicacion;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;

import java.util.List;

public class Tecnico {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer DNI;
    private Integer CUIL;
    private List<MedioDeComunicacion> mediosDeComunicacion;
    private Integer areaCobertura;
    @Getter
    private Coordenada coordenada;
    private Boolean disponible;

    public Tecnico(String nombre, String apellido, TipoDocumento tipoDocumento, Integer DNI, Integer CUIL, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.DNI = DNI;
        this.CUIL = CUIL;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
    }
}
