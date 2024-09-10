package ar.edu.utn.frba.dds.models.entities.lectorCSV;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion.FormaDeColaboracionFactory;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.factoryFormasDeColoaboracion.FormaDeColaboracionFactoryProvider;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.ServicioMail;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoFormaDeColaboracion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import lombok.Getter;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

@Getter
public class LectorColaborador extends LectorDeCSV {

    private Set<Colaborador> colaboradorLeido;

    public LectorColaborador() {
        this.colaboradorLeido = new HashSet<>();
    }

    @Override
    public void levantarObjetos(List<String[]> csvComoLista) {
        for (String[] strings : csvComoLista) {

            // tomo la primer lista de strings
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(strings[0]);
            Integer numeroDocumento = Integer.valueOf(strings[1]);
            String nombre = strings[2];
            String apellido = strings[3];
            String contacto = strings[4];
            SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = new Date();
            Date fechaColaboracion = new Date();
            try{
                fechaColaboracion =  formatter.parse(strings[5]);
                //fechaColaboracion= fecha;
            }catch(java.text.ParseException e){
                System.out.println("Error al parsear la fecha: " + e.getMessage());
            }

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


            if (!esColaboradorCargado(numeroDocumento, tipoDocumento)) {
                Colaborador colaborador = new Colaborador(numeroDocumento,tipoDocumento,nombre, apellido, null);
                colaborador.setTipoPersona(TipoPersona.HUMANA);
                // FormaDeColaboracion colaboracion;
                colaboradorLeido.add(colaborador);
                RepoColaborador.INSTANCE.agregar(colaborador);

                ServicioMail.getInstance().enviarCorreo(contacto, "Gracias por colaborar con nosotros","Gracias por colaborar" );
            }
                Colaborador colaboradorCargado = obtenerColaborador(numeroDocumento, tipoDocumento);
                FormaDeColaboracion colaboracion = obtenerColaboracion(formaDeColaboracion, cantidad, fechaColaboracion);

                colaboradorCargado.agregarColaboracionRealizada(colaboracion);
                RepoColaborador.INSTANCE.modificar(colaboradorCargado);


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

    private Colaborador obtenerColaborador(Integer numeroDocumento, TipoDocumento tipoDocumento) {
        for (Colaborador colaborador : colaboradorLeido) {
            if (colaborador.getNumeroDocumento().equals(numeroDocumento) && colaborador.getTipoDocumento().equals(tipoDocumento)) {
                return colaborador;
            }
        }
        return null;
    }

    public void impirmirColaboracionesRealizadas (){
        colaboradorLeido.forEach(colaborador -> {
            System.out.println("Colaborador: " + colaborador.getNombre() + " " + colaborador.getApellido());
            System.out.println(colaborador.getColaboracionesRealizadas().size());
        });
    }

    public FormaDeColaboracion obtenerColaboracion(TipoColaboracion formaDeColaboracion, Integer cantidad, Date fechaColaboracion) {
        FormaDeColaboracionFactoryProvider factoryProvider = new FormaDeColaboracionFactoryProvider();
        FormaDeColaboracionFactory factory = factoryProvider.getFactory(formaDeColaboracion);
        return factory.create(cantidad, fechaColaboracion);
    }

}
