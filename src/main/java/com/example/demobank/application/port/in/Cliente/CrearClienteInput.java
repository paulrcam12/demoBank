package com.example.demobank.application.port.in.Cliente;

import com.example.demobank.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface CrearClienteInput {
    public Cliente crearCliente(Cliente cliente);
}
