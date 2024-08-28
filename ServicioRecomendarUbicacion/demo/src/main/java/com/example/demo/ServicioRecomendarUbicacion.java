package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServicioRecomendarUbicacion {

    // Clase interna para representar una ubicación


    // Método para recibir una ubicación y devolver ubicaciones cercanas
    @GetMapping("/nearby")
    public List<Ubicacion> getNearbyLocations(@RequestParam double lat, @RequestParam double lon, @RequestParam int radius) {
        // Hardcodeamos algunas ubicaciones
        List<Ubicacion> ubicaciones = new ArrayList<>();
        ubicaciones.add(new Ubicacion("Plaza Mayor", -34.6083, -58.3712));
        ubicaciones.add(new Ubicacion("Obelisco", -34.6037, -58.3816));
        ubicaciones.add(new Ubicacion("Puerto Madero", -34.6177, -58.3629));

        // Aquí podrías filtrar por cercanía (esto se puede mejorar más adelante)
        // Por ahora devolvemos todas las ubicaciones hardcodeadas
        return ubicaciones;
    }
}


//@RestController()
//public class ServicioRecomendarUbicacion {
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "¡Hola, Spring Boot!";
//    }
//
//}
