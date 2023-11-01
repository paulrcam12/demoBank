package com.example.demobank.application.port.out.Cliente;

import org.springframework.stereotype.Component;

@Component
public interface CheckIfClienteExist {

    boolean checkIfExist(Integer id);

}
