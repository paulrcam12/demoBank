package com.example.demobank.application.port.out.Cuenta;

import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public interface LeerCuentaOutput {
    Cuenta leerById(Integer id);
}
