package ar.edu.utn.frba.dds.heladera;

public class Accionador {

    private static Accionador instancia = null;

    public static Accionador getInstancia(){
        if (instancia == null){
            instancia = new Accionador();
        }
        return instancia;
    }
    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        RegistroDeAlerta registro = new RegistroDeAlerta(tipoAlerta);
        heladera.agregarRegistroDeAlerta(registro);
    }
}