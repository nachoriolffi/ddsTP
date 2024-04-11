package ar.edu.utn.frba.dds.validador;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContrasenia {

    private List<Validar> validadores;

    public ValidadorDeContrasenia(){
        this.validadores= new ArrayList<Validar>();
    }

    public ValidadorDeContrasenia(List<Validar> validadores){
        this.validadores= validadores;
    }

    public boolean validarContrasenia ( String contrasenia){
        return validadores.stream().allMatch(validador -> validador.validar(contrasenia));
    }

    public void agregarValidador(Validar validador){
        validadores.add(validador);
    }

}
