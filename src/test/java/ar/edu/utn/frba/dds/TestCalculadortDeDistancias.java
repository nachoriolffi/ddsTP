package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.distancias.CalculadorDistancias;
import ar.edu.utn.frba.dds.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.heladera.Heladera;
import ar.edu.utn.frba.dds.tecnico.Tecnico;
import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCalculadortDeDistancias {

    private List<Tecnico> tecnicos= new ArrayList<Tecnico>();
    private Heladera heladera;

    @BeforeEach
    public void setUp() {

        Tecnico tecnico1 = new Tecnico("Juan", "Perez", TipoDocumento.DNI, 123456, 123456, null, 100);
        Coordenada coordenada1= new Coordenada(40.7128, 74.0060);
        tecnico1.setCoordenada(coordenada1); // Nueva York

        Tecnico tecnico2 = new Tecnico("Pedro", "Gomez", TipoDocumento.DNI, 234567, 234567, null, 100);
        Coordenada coordenada2 = new Coordenada(34.0522, 118.2437);
        tecnico2.setCoordenada(coordenada2); // Los Angeles

        Tecnico tecnico3 = new Tecnico("Carlos", "Rodriguez", TipoDocumento.DNI, 345678, 345678, null, 100);
        Coordenada coordenada3 = new Coordenada(41.8781, 87.6298);
        tecnico3.setCoordenada(coordenada3); // Chicago

        tecnicos.add(tecnico1);
        tecnicos.add(tecnico2);
        tecnicos.add(tecnico3);

        heladera= new Heladera(null, new Coordenada(39.7128, 74.0060), 150, null); // Nueva York

    }

    @Test
    public void testCalculadorDistancia(){

        CalculadorDistancias calculadorDistancias = CalculadorDistancias.getInstance();
        CalculadorDistanciasTecnicoHeladera calculadorDistanciasTecnicoHeladera = CalculadorDistanciasTecnicoHeladera.getInstance();

        // ya tenemos los dos calculadores para hacer el test ahora tenemos que ver que devuelve

       Tecnico tecnicoMasCercano= calculadorDistanciasTecnicoHeladera.calcularTecnicoMasCercano(tecnicos, heladera);
       System.out.println("El tecnico mas cercano es el que tiene el DNI: "+tecnicoMasCercano.getDNI());

       assert tecnicoMasCercano.getCoordenada().getLatitud()==40.7128;
    }
}
