package com.example.demobank.application.port.out.Cuenta;

import org.springframework.stereotype.Component;

@Component
public interface CheckIfCuentaExist {
    boolean checkIfExist(Integer id);

}
