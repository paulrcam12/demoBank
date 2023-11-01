package com.example.demobank.application.port.in.Cuenta;

import com.example.demobank.domain.Cuenta;

public interface ObtenerCuentaInput {

    public Cuenta leer(Integer id);
}
