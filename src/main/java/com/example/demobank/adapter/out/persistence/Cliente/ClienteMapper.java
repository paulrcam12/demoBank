package com.example.demobank.adapter.out.persistence.Cliente;

import com.example.demobank.adapter.out.persistence.Persona.PersonaEntity;
import com.example.demobank.domain.Cliente;

public class ClienteMapper {

    public static Cliente entityToDomain(ClienteEntity clienteEntity) {
        Cliente cliente = new Cliente(clienteEntity.getPersona().getIdentificacion(),
                clienteEntity.getPersona().getNombre(),
                clienteEntity.getPersona().getGenero(),
                clienteEntity.getPersona().getEdad(),
                clienteEntity.getPersona().getDireccion(),
                clienteEntity.getPersona().getTelefono(),
                clienteEntity.getClienteId(),
                clienteEntity.getContrasena(),
                clienteEntity.isEstado()
                );

        return cliente;
    }

    public static ClienteEntity domainToEntity(Cliente cliente){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setClienteId(cliente.getClienteId());
        clienteEntity.setContrasena(cliente.getContrasena());
        clienteEntity.setEstado(cliente.isEstado());


        PersonaEntity personaEntity = new PersonaEntity(cliente.getIdentificacion()
        ,cliente.getNombre(), cliente.getGenero(), cliente.getEdad(), cliente.getDireccion(), cliente.getTelefono());

        clienteEntity.setPersona(personaEntity);



        return clienteEntity;

    }
}
