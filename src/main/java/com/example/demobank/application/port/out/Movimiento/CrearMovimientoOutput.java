package com.example.demobank.application.port.out.Movimiento;

import com.example.demobank.domain.Movimiento;
import org.springframework.stereotype.Component;

@Component
public interface CrearMovimientoOutput {

    Movimiento crear(Movimiento movimiento);

}
