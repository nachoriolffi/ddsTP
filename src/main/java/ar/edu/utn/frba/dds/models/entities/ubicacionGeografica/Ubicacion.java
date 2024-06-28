package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica;

public class Ubicacion {
    private Provincia provincia;
    private Municipio municipio;
    private Localidad localidad;
    private Coordenada coordenada;

    public Ubicacion(Provincia provincia, Municipio municipio, Localidad localidad, Coordenada coordenada) {
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.coordenada = coordenada;
    }
}
