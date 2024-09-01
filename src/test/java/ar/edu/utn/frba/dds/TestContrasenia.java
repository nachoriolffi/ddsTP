package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.validador.ValidadorDeContrasenia;
import ar.edu.utn.frba.dds.models.entities.validador.FormaValidar10MilPeoresContrasenias;
import ar.edu.utn.frba.dds.models.entities.validador.FormaValidarLongitud;
import org.junit.jupiter.api.Test;

public class TestContrasenia {

    @Test
    public void testContraseniaValida(){
        ValidadorDeContrasenia validador = new ValidadorDeContrasenia();
        validador.agregarValidador(new FormaValidarLongitud());
        validador.agregarValidador(new FormaValidar10MilPeoresContrasenias());

        Usuario pepe = new Usuario("pepe", "782jhg *+++jhg4e");
        assert validador.validarContrasenia("782jhg *+++jhg4e",pepe);

    }


}
