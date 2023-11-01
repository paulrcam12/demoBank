package com.example.demobank.application.port.out.Cuenta;

import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public interface EliminarCuentaOutput {

    public Cuenta eliminarById(Integer id);

}
