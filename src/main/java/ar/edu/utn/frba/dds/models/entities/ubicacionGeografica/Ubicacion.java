package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.*;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Ubicacion;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Provincia", referencedColumnName = "id_Provincia")
    private Provincia provincia;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Municipio", referencedColumnName = "id_Municipio")
    private Municipio municipio;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Localidad", referencedColumnName = "id_Localidad")
    private Localidad localidad;
    //@ManyToOne
    //private Coordenada coordenada;

    public Ubicacion(Provincia provincia, Municipio municipio, Localidad localidad) {
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;

    }
}
