package ar.edu.utn.frba.dds.dtos;

public class CalleDTO {
    private String nombre;
    private Integer altura;

    // Constructor
    public CalleDTO(String nombre, Integer altura) {
        this.nombre = nombre;
        this.altura = altura;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Integer getAltura() {
        return altura;
    }
}
