package ar.edu.utn.frba.dds.dtos;


public class PuntoRecomendadoDTO {
    String direccion;
    public PuntoRecomendadoDTO(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
