package com.example.demobank.application.port.out.Cliente;

import com.example.demobank.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface EliminarClienteOutput {
    Cliente eliminarById(Integer id);
}
