package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.colaborador.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LectorColaborador extends LectorDeCSV{

    @Getter
    private Set<Colaborador> colaboradorLeido;
    public LectorColaborador() {
        this.colaboradorLeido= new HashSet<>();
    }

    @Override
    public void levantarObjetos(List<String[]> csvComoLista) {
        for(int i =0 ; i<csvComoLista.size() ; i++){

            String[] strings = csvComoLista.get(i);
            // tomo la pimer lista de strings
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(strings[0]);
            String numeroDocumento = strings[1];
            String nombre = strings[2];
            String apellido = strings[3];
            String contacto = strings[4];

            Colaborador colaborador = new Colaborador(tipoDocumento,numeroDocumento,nombre,apellido,null);
            if (!esColaboradorCargado(numeroDocumento)) {
                colaboradorLeido.add(colaborador);
            }

            //una vez terminado la forma de colaboracion agrego un else que hace la logica de si ya contiene para agregarle la donacion realizada
        }


    }

    private boolean esColaboradorCargado(String numeroDocumento) {
        for (Colaborador colaborador : colaboradorLeido) {
            if (colaborador.getNumeroDocumento().equals(numeroDocumento)) {
                return true;
            }
        }
        return false;
    }

}