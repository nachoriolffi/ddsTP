package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ListadoCoordenadas {
    private int cantidad;
    private List<Direccion> direcciones;

    @Getter
    @Setter
    public static class Direccion {
        private Altura altura;
        private Calle calle;
        private String nomenclatura;
        private Ubicacion ubicacion;

        @Getter
        @Setter
        public static class Altura {
            private int valor;
        }

        @Getter
        @Setter
        public static class Calle {
            private String id;
            private String nombre;
        }

        @Getter
        @Setter
        public static class Ubicacion {
            private Double lat; // Latitud
            private Double lon; // Longitud
        }
    }

    // Getters and Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return "ListadoCoordenadas{" +
                "cantidad=" + cantidad +
                ", direcciones=" + direcciones +
                '}';
    }
}


