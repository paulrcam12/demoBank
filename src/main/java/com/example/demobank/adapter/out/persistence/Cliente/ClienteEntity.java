package com.example.demobank.adapter.out.persistence.Cliente;

import com.example.demobank.adapter.out.persistence.Cuenta.CuentaEntity;
import com.example.demobank.adapter.out.persistence.Persona.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    private Integer clienteId;
    private String contrasena;
    private boolean estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    List<CuentaEntity> cuenta;
}
