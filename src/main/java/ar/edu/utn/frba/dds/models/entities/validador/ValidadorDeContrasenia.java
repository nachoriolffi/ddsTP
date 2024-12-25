package ar.edu.utn.frba.dds.models.entities.validador;

import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContrasenia {

    private List<FormaValidar> validadores;

    public ValidadorDeContrasenia(){
        this.validadores= new ArrayList<FormaValidar>();

    }

    public ValidadorDeContrasenia(List<FormaValidar> validadores){
        this.validadores= validadores;
    }

    public boolean validarContrasenia (String contrasenia, Usuario usuario){
        return validadores.stream().allMatch(validador -> validador.validar(contrasenia, usuario)) && validarIgualdadContrasenia(contrasenia, usuario);
    }

    public boolean validarContraseniaModificar (String contrasenia, Usuario usuario){
        return validadores.stream().allMatch(validador -> validador.validar(contrasenia, usuario)) && !validarIgualdadContrasenia(contrasenia, usuario);
    }

    public boolean validarIgualdadContrasenia (String contrasenia, Usuario usuario){
        if(!contrasenia.equals(usuario.getContrasenia()))
        {
            System.err.println("Error: Las contraseñas no coinciden");
            return false;
        }
        return true;
    }

    public void agregarValidador(FormaValidar validador){
        validadores.add(validador);
    }

    public void instanciarFormaValidar(){
        this.agregarValidador(new FormaValidarLongitud());
        this.agregarValidador(new FormaValidar10MilPeoresContrasenias());
    }

}
