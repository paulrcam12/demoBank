package com.example.demobank.application.port.in.Cuenta;

import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public interface EliminarCuentaInput {
    Cuenta eliminar(Integer id);
}
