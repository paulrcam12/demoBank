package com.example.demobank.adapter.out.persistence.Persona;

import com.example.demobank.adapter.out.persistence.Cliente.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id
    private int identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private String telefono;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private ClienteEntity cliente;

    public PersonaEntity() {
    }

    public PersonaEntity(int identificacion, String nombre, String genero, int edad, String direccion, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}

