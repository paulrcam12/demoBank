package com.example.demobank.application.port.in.Cuenta;

import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public interface EditarCuentaInput {

    Cuenta editar(Cuenta cuenta);

}
