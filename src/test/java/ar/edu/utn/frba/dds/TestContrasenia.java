package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.validador.ValidadorDeContrasenia;
import ar.edu.utn.frba.dds.validador.Validar10MilPeoresContrasenias;
import ar.edu.utn.frba.dds.validador.ValidarLongitud;
import org.junit.jupiter.api.Test;

public class TestContrasenia {

    @Test
    public void testContraseniaValida(){
        ValidadorDeContrasenia validador = new ValidadorDeContrasenia();
        validador.agregarValidador(new ValidarLongitud());
        validador.agregarValidador(new Validar10MilPeoresContrasenias());

        assert validador.validarContrasenia("7824naufede");

    }


}
