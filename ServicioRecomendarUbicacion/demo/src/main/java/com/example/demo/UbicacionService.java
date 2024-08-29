package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Coordenada> obtenerCercanas(double latitud, double longitud, double radio) {
        return ubicacionRepository.encontrarCercanas(latitud, longitud, radio);
    }
}