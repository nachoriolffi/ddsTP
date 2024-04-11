package ar.edu.utn.frba.dds.validador;

public class ValidarLongitud implements Validar{
    private static final int MINIMO = 8;
    private static final int MAXIMO = 64;



    public boolean validar(String contrasenia) {
        return contrasenia.length() >= ValidarLongitud.MINIMO && contrasenia.length() <= ValidarLongitud.MAXIMO;
    }

    //constructor
    public ValidarLongitud() {
    }
}
