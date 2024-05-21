package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class LectorColaborador extends LectorDeCSV {

    private Set<Colaborador> colaboradorLeido;

    public LectorColaborador() {
        this.colaboradorLeido = new HashSet<>();
    }

    @Override
    public void levantarObjetos(List<String[]> csvComoLista) {
        for (String[] strings : csvComoLista) {

            // tomo la pimer lista de strings
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(strings[0]);
            Integer numeroDocumento = Integer.valueOf(strings[1]);
            String nombre = strings[2];
            String apellido = strings[3];
            String contacto = strings[4];

            System.out.println("tipoDocumento: " + tipoDocumento);
            System.out.println("numeroDocumento: " + numeroDocumento);
            System.out.println("nombre: " + nombre);
            System.out.println("apellido: " + apellido);
            System.out.println("contacto: " + contacto);

            Colaborador colaborador = new Colaborador(numeroDocumento,tipoDocumento,nombre, apellido, null);
            if (!esColaboradorCargado(numeroDocumento, tipoDocumento)) {
                colaboradorLeido.add(colaborador);
            }

            //una vez terminado la forma de colaboracion agrego un else que hace la logica de si ya contiene para agregarle la donacion realizada
        }


    }

    private boolean esColaboradorCargado(Integer numeroDocumento, TipoDocumento tipoDocumento) {
        for (Colaborador colaborador : colaboradorLeido) {
            if (colaborador.getNumeroDocumento().equals(numeroDocumento) && colaborador.getTipoDocumento().equals(tipoDocumento)) {
                return true;
            }
        }
        return false;
    }

}
