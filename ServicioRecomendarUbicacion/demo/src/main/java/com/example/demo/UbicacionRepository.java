package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionRepository extends JpaRepository<Coordenada, Long> {
    @Query("SELECT u FROM Coordenada u WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(u.latitud)) * cos(radians(u.longitud) - radians(:lng)) + sin(radians(:lat)) * sin(radians(u.latitud)))) < :radio")
    List<Coordenada> encontrarCercanas(@Param("lat") double latitud, @Param("lng") double longitud, @Param("radio") double radio);

}