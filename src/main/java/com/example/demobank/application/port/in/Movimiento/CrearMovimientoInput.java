package com.example.demobank.application.port.in.Movimiento;

import com.example.demobank.domain.Movimiento;
import org.springframework.stereotype.Component;

@Component
public interface CrearMovimientoInput {

    public Movimiento crear(Movimiento movimiento);
}
