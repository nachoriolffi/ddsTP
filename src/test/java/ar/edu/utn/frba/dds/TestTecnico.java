package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.distancias.CalculadorDistanciasTecnicoHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestTecnico {
    Tecnico tecnico1;
    Tecnico tecnico2;
    Tecnico tecnico3;
    Tecnico tecnico4;
    Heladera heladera1;
    List<Tecnico> tecnicos = new ArrayList<>();


    @BeforeEach
    void setUp() throws Exception {

        tecnico1 = new Tecnico("Juan", "Dominguez", new Coordenada(-34.59857735217844, -58.41998551910442), true    , 20);
        tecnico2 = new Tecnico("Roberto", "Perez", new Coordenada(-34.597484308117835, -58.40342937695181), false, 50);
        tecnico3 = new Tecnico("Eduardo", "Santo", new Coordenada(-34.598998884564914, -58.40387036612783), true, 100);
        tecnico4 = new Tecnico("Ignacio", "Cruz", new Coordenada(-34.58999144061407, -58.40887711497087), true, 100);
        heladera1 = new Heladera(new Direccion("Medrano", 951, 1), new Coordenada(-34.59857735217844, -58.41998551910442), false);
        tecnicos.add(tecnico1);
        tecnicos.add(tecnico2);
        tecnicos.add(tecnico3);
        tecnicos.add(tecnico4);

    }

    @Test
    void testTecnicoRegistraVisita() {

        /*CalculadorDistanciasTecnicoHeladera calculadorDistanciasTecnicoHeladera = CalculadorDistanciasTecnicoHeladera.getInstance();
        Tecnico tecnicoMasCercano = calculadorDistanciasTecnicoHeladera.calcularTecnicoMasCercano(tecnicos, heladera1);
        System.out.println(tecnicoMasCercano.getNombre()+ " " + tecnicoMasCercano.getApellido());
        assert tecnicoMasCercano.getCoordenada().getLatitud() == -34.59857735217844;
        RegistroVisita visita = new RegistroVisita();
        visita.setDescripcion("Visita 1");
        visita.setFechaVisita(new Date());
        tecnicoMasCercano.registrarVisita(visita);
        RepoRegistrosVisita repo = new RepoRegistrosVisita();
        assert repo.buscarTodos().size() == 1;*/

    }
}
