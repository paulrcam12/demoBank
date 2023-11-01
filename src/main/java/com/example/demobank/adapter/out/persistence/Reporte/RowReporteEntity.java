package com.example.demobank.adapter.out.persistence.Reporte;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class RowReporteEntity {

    @Id
    private Date fecha;
    private String nombre;
    private Integer numero_cuenta;
    private String tipo_cuenta;
    private Double saldo_inicial;
    private boolean estado;
    private Double valor;
    private Double saldo;
}
