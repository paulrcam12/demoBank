package com.example.demobank.adapter.out.persistence.Cuenta;

import com.example.demobank.adapter.out.persistence.Cliente.ClienteEntity;
import com.example.demobank.adapter.out.persistence.Movimiento.MovimientoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class CuentaEntity {

    @Id
    private Integer numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovimientoEntity> movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

}
