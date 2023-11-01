package com.example.demobank.application.port.out.Movimiento;

import org.springframework.stereotype.Component;

@Component
public interface CheckIfMovimientoExist {

    boolean checkIfExist(Integer id);

}
