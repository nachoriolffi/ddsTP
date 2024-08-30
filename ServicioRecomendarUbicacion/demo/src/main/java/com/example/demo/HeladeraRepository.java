package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {

    @Query("SELECT c FROM Coordenada c JOIN Heladera h ON c.id_Coordenada = h.coordenada.id_Coordenada WHERE (6371 * acos( cos(radians(:lat)) * cos(radians(c.latitud)) * cos(radians(c.longitud) - radians(:lng)) + sin(radians(:lat)) * sin(radians(c.latitud)))) < :radio")
    List<Coordenada> encontrarCercanas(@Param("lat") double latitud, @Param("lng") double longitud, @Param("radio") double radio);
}