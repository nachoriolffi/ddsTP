package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {

    @Id
    //@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_Localidad;
    @Column(name = "localidad", columnDefinition="VARCHAR(50)", nullable = false)
    private String localidad;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Municipio", referencedColumnName = "id_Municipio")
    private Municipio municipio;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Provincia", referencedColumnName = "id_Provincia")
    private Provincia provincia;
    //@Column(name = "codigoPostal")
   // private Integer codigoPostal;
}
