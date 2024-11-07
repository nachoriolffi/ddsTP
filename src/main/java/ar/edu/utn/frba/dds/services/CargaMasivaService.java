package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.entities.lectorCSV.LectorColaborador;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import java.io.File;

public class CargaMasivaService {
    public void realizarLecturaCSV(UploadedFile file) {
        String rutaUploads = "src/main/resources/uploads/";
        String filePath = rutaUploads + file.filename();
        try {
            FileUtil.streamToFile(file.content(), filePath);
            LectorColaborador lectorCSV = new LectorColaborador();
            lectorCSV.leerCSV(filePath);
        } finally {
            // eliminamos el archivo procesado para liberar espacio
            // es una opcion que podemos descomentar luego
            /*File uploadedFile = new File(filePath);
            if (uploadedFile.exists()) {
                uploadedFile.delete();
            }*/
        }


    }
}
