package com.example.demobank.application.services;

import com.example.demobank.application.port.in.Cliente.CrearClienteInput;
import com.example.demobank.application.port.in.Cliente.EditarClienteInput;
import com.example.demobank.application.port.in.Cliente.EliminarClienteInput;
import com.example.demobank.application.port.out.Cliente.CheckIfClienteExist;
import com.example.demobank.application.port.out.Cliente.CrearClienteOutput;
import com.example.demobank.application.port.out.Cliente.EditarClienteOutput;
import com.example.demobank.application.port.out.Cliente.EliminarClienteOutput;
import com.example.demobank.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteCRUDService implements CrearClienteInput, EditarClienteInput, EliminarClienteInput {

    private final CrearClienteOutput crearClienteOutput;
    private final EditarClienteOutput editarClienteOutput;
    private final EliminarClienteOutput eliminarClienteOutput;
    private final CheckIfClienteExist checkIfClienteExist;

    public ClienteCRUDService(CrearClienteOutput crearClienteOutput,
                              EditarClienteOutput editarClienteOutput,
                              EliminarClienteOutput eliminarClienteOutput,
                              CheckIfClienteExist checkIfClienteExist) {
        this.crearClienteOutput = crearClienteOutput;
        this.editarClienteOutput = editarClienteOutput;
        this.eliminarClienteOutput = eliminarClienteOutput;
        this.checkIfClienteExist = checkIfClienteExist;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        boolean exist = checkIfClienteExist.checkIfExist(cliente.getClienteId());
        if(exist){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente "+cliente.getClienteId()+"ya existe.");
        }
        return crearClienteOutput.guardar(cliente);
    }

    @Override
    public Cliente editar(Cliente cliente) {
        boolean exist = checkIfClienteExist.checkIfExist(cliente.getClienteId());
        if(!exist){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente "+cliente.getClienteId()+"ya existe.");
        }
        return editarClienteOutput.editar(cliente);
    }

    @Override
    public Cliente eliminarById(Integer id) {
        boolean exist = checkIfClienteExist.checkIfExist(id);
        if(!exist){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente "+id+"ya existe.");
        }
        return eliminarClienteOutput.eliminarById(id);
    }
}
