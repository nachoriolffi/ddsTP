package com.example.demo;

import com.example.demo.Coordenada;
import com.example.demo.UbicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Locations", description = "Obtiene las ubicaciones más cercanas en base a una lat,long y radio")
public class ControllerRecomendarUbicacion {

    @Autowired
    private UbicacionService ubicacionService;

    @Operation(summary = "Get nearby locations", description = "Returns a list of nearby locations based on latitude, longitude, and radius")
    @GetMapping("/nearby")
    public List<Coordenada> getNearbyLocations(
            @Parameter(description = "Latitude") @RequestParam double lat,
            @Parameter(description = "Longitude") @RequestParam double lon,
            @Parameter(description = "Radius in kilometers") @RequestParam int radius) {
        List<Coordenada> coords = ubicacionService.obtenerCercanas(lat, lon, radius);
        if (!coords.isEmpty()) {
            for (Coordenada coord : coords) {
                System.out.println("Coordenada encontrada: " + coord);
            }
        } else {
            System.out.println("No se encontraron ubicaciones cercanas para lat=" + lat + ", lon=" + lon + ", radius=" + radius);
        }
        return coords; // Devolver directamente la lista de coordenadas
    }
}