package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.colaborador.Colaborador;
import ar.edu.utn.frba.dds.colaborador.formas.FormaDeColaboracion;
import ar.edu.utn.frba.dds.colaborador.formas.TipoColaboracion;
import ar.edu.utn.frba.dds.contacto.ServicioMail;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;

import java.util.Date;
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
            Date fechaColaboracion = new Date(strings[5]);
            TipoColaboracion formaDeColaboracion =TipoColaboracion.valueOf(strings[6]);
            Integer cantidad = Integer.valueOf(strings[7]);

            System.out.println("tipoDocumento: " + tipoDocumento);
            System.out.println("numeroDocumento: " + numeroDocumento);
            System.out.println("nombre: " + nombre);
            System.out.println("apellido: " + apellido);
            System.out.println("contacto: " + contacto);
            System.out.println("fechaColaboracion: " + fechaColaboracion);
            System.out.println("formaDeColaboracion: " + formaDeColaboracion);
            System.out.println("cantidad: " + cantidad);

            Colaborador colaborador = new Colaborador(numeroDocumento,tipoDocumento,nombre, apellido, null);
            if (!esColaboradorCargado(numeroDocumento, tipoDocumento)) {
                FormaDeColaboracion colaboracion;
                colaboradorLeido.add(colaborador);
                ServicioMail.getInstance().enviarCorreo("Gracias por colaborar", "Gracias por colaborar con nosotros", contacto);
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

    /*public FormaDeColaboracion obtenerColaboracion(FormaDeColaboracion formaDeColaboracion, Integer cantidad) {
        FormaDeColaboracion colaboracion = null;
        switch (formaDeColaboracion) {
            case DINERO:
                colaboracion = new FormaDeColaboracion() {
                    @Override
                    public void sumarPuntosA(Colaborador colaborador) {
                        colaborador.setPuntosTotales(colaborador.getPuntosTotales() + cantidad);
                    }
                };
                break;
            case DONACION_VIANDAS:
                colaboracion = new FormaDeColaboracion() {
                    @Override
                    public void sumarPuntosA(Colaborador colaborador) {
                        colaborador.setPuntosTotales(colaborador.getPuntosTotales() + cantidad);
                    }
                };
                break;
            case REDISTRIBUCION_VIANDAS:
                colaboracion = new FormaDeColaboracion() {
                    @Override
                    public void sumarPuntosA(Colaborador colaborador) {
                        colaborador.setPuntosTotales(colaborador.getPuntosTotales() + cantidad);
                    }
                };
                break;
            case ENTREGA_TARJETAS:
                colaboracion = new FormaDeColaboracion() {
                    @Override
                    public void sumarPuntosA(Colaborador colaborador) {
                        colaborador.setPuntosTotales(colaborador.getPuntosTotales() + cantidad);
                    }
                };
                break;
        }
        return colaboracion;
    }*/

}
