package ar.edu.utn.frba.dds.models.entities.tecnico;

import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Tecnico {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String apellido;
    private TipoDocumento tipoDocumento;
    @Getter @Setter
    private Integer DNI;
    private Integer CUIL;
    @Getter @Setter
    private List<MedioDeComunicacion> mediosDeComunicacion;
    private Integer areaCobertura;
    @Getter @Setter
    private Coordenada coordenada;
    @Getter @Setter
    private Boolean disponible;
    @Getter @Setter
    private List<Contacto> contactos;


    public Tecnico(Long id, String nombre, String apellido, TipoDocumento tipoDocumento, Integer DNI, Integer CUIL, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.DNI = DNI;
        this.CUIL = CUIL;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
    }

    public Tecnico(Long id, String nombre, String apellido, Coordenada coordenada, Boolean disponible, Integer areaCobertura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.coordenada = coordenada;
        this.disponible = disponible;
        this.areaCobertura = areaCobertura;
    }

    public Tecnico(Long id, String nombre, String apellido, TipoDocumento tipoDocumento, Integer DNI, Integer CUIL, List<MedioDeComunicacion> mediosDeComunicacion, Integer areaCobertura, Coordenada coordenada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.DNI = DNI;
        this.CUIL = CUIL;
        this.mediosDeComunicacion = mediosDeComunicacion;
        this.areaCobertura = areaCobertura;
        this.coordenada = coordenada;
    }

    public void registrarVisita(RegistroVisita registro){
        RepoRegistrosVisita.getInstancia().agregarRegistroVisita(registro);
    }

}
