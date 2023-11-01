package com.example.demobank.application.port.out.Persona;

import org.springframework.stereotype.Component;

@Component
public interface CheckIfPersonaExist {

    boolean checkIfExist(Integer id);

}
