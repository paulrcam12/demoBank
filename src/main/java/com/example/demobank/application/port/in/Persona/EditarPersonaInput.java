package com.example.demobank.application.port.in.Persona;

import com.example.demobank.domain.Persona;
import org.springframework.stereotype.Component;

@Component
public interface EditarPersonaInput {
    Persona editar(Persona persona);
}
