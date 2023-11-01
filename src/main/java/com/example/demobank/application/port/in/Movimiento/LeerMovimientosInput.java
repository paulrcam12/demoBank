package com.example.demobank.application.port.in.Movimiento;

import com.example.demobank.domain.Movimiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeerMovimientosInput {
    List<Movimiento> getAll();
}
