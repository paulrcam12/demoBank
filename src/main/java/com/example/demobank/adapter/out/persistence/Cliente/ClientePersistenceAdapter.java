package com.example.demobank.adapter.out.persistence.Cliente;

import com.example.demobank.application.port.out.Cliente.CheckIfClienteExist;
import com.example.demobank.application.port.out.Cliente.CrearClienteOutput;
import com.example.demobank.application.port.out.Cliente.EditarClienteOutput;
import com.example.demobank.application.port.out.Cliente.EliminarClienteOutput;
import com.example.demobank.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClientePersistenceAdapter implements CrearClienteOutput, EditarClienteOutput, EliminarClienteOutput, CheckIfClienteExist {

    private final ClienteRepository clienteRepository;

    public ClientePersistenceAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.domainToEntity(cliente);
        clienteRepository.save(clienteEntity);
        return cliente;
    }

    @Override
    public Cliente editar(Cliente cliente) {

        ClienteEntity clienteEntity = ClienteMapper.domainToEntity(cliente);
        clienteRepository.save(clienteEntity);
        return cliente;
    }

    @Override
    public Cliente eliminarById(Integer id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).get();
        clienteRepository.delete(clienteEntity);
        return ClienteMapper.entityToDomain(clienteEntity);
    }
    @Override
    public boolean checkIfExist(Integer id) {
        return clienteRepository.existsById(id);
    }
}
