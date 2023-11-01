package com.example.demobank.application.port.in.Cuenta;

import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public interface CrearCuentaInput {

    public Cuenta crear(Cuenta cuenta);

}
