package com.example.demobank.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Persona {
    private int identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private String telefono;

}
