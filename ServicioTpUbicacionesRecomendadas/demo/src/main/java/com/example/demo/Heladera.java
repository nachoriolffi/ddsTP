package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Heladera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Heladera;

    @OneToOne
    @JoinColumn(name = "id_Coordenada", referencedColumnName = "id_Coordenada")    private Coordenada coordenada;

    // Otros atributos, constructores, getters y setters
}