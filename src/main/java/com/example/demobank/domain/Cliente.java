package com.example.demobank.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@ToString

public class Cliente extends Persona{

    private int clienteId;
    private String contrasena;
    private boolean estado;



//    public Cliente(int identificacion, String nombre, String genero, int edad, String direccion, String telefono) {
//        super(identificacion, nombre, genero, edad, direccion, telefono);
//    }

    @JsonCreator
    public Cliente(@JsonProperty("identificacion") int identificacion,
                   @JsonProperty("nombre") String nombre,
                   @JsonProperty("genero") String genero,
                   @JsonProperty("edad") int edad,
                   @JsonProperty("direccion") String direccion,
                   @JsonProperty("telefono") String telefono,
                   @JsonProperty("clienteId") int clienteId,
                   @JsonProperty("contrasena") String contrasena,
                   @JsonProperty("estado") boolean estado){
        super(identificacion, nombre, genero, edad, direccion, telefono);
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }




}
