package com.example.demobank.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class RowReporte {
    private Date fecha;
    private String nombre;
    private Integer numero_cuenta;
    private String tipo_cuenta;
    private Double saldo_inicial;
    private boolean estado;
    private Double valor;
    private Double saldo;
}
