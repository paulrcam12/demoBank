package com.example.demobank.application.port.out.Movimiento;

import com.example.demobank.domain.Movimiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeerMovimientosOutput {

    List<Movimiento> getAll();

}
