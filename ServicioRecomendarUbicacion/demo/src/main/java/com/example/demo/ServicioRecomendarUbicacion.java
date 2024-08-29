package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicioRecomendarUbicacion {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping("/nearby")
    public List<Coordenada> getNearbyLocations(@RequestParam double lat, @RequestParam double lon, @RequestParam int radius) {
        return ubicacionRepository.encontrarCercanas(lat, lon, radius);
    }
}



