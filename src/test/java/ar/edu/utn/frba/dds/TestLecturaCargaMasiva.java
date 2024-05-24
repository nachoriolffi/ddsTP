package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.lectorCSV.LectorColaborador;
import org.junit.jupiter.api.Test;



public class TestLecturaCargaMasiva {

    @Test
    public void TestCargaMasiva(){
        String path = "src/test/java/ar/edu/utn/frba/dds/archivo_ejemplo.csv";
        LectorColaborador lector= new LectorColaborador();

        lector.leerCSV(path);
        System.out.println((lector.getColaboradorLeido()));
        System.out.println(lector.getColaboradorLeido().size());
        //imprimir la cantidad de donaciones realizadas por el colaborador

        assert lector.getColaboradorLeido().size() == 3;

    }
}
