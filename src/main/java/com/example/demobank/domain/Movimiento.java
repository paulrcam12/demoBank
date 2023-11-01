package com.example.demobank.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movimiento {
    private Integer idMovimiento;
    private Date fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
    private Integer idCuenta;


}
