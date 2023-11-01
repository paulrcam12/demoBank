package com.example.demobank.application.port.out.Persona;

import com.example.demobank.domain.Persona;
import org.springframework.stereotype.Component;

@Component
public interface EditarPersonaOutput {
    Persona editar(Persona persona);
}
