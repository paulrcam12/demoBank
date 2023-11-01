package com.example.demobank.adapter.out.persistence.Cuenta;

import com.example.demobank.adapter.out.persistence.Cliente.ClienteEntity;
import com.example.demobank.adapter.out.persistence.Cliente.ClienteMapper;
import com.example.demobank.adapter.out.persistence.Cliente.ClienteRepository;
import com.example.demobank.application.port.out.Cuenta.*;
import com.example.demobank.domain.Cliente;
import com.example.demobank.domain.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaPersistenceAdapter implements CrearCuentaOutput, ActualizarCuentaOutput, LeerCuentaOutput, EliminarCuentaOutput, CheckIfCuentaExist {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaPersistenceAdapter(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        ClienteEntity clienteEntity = clienteRepository.findById(cuenta.getClienteId()).get();
        Cliente cliente = ClienteMapper.entityToDomain(clienteEntity);
        CuentaEntity cuentaEntity = CuentaMapper.domainToEntity(cuenta, cliente);
        cuentaRepository.save(cuentaEntity);
        return cuenta;
    }

    @Override
    public Cuenta editar(Cuenta cuenta) {

        ClienteEntity clienteEntity = clienteRepository.findById(cuenta.getClienteId()).get();
        Cliente cliente = ClienteMapper.entityToDomain(clienteEntity);
        CuentaEntity cuentaEntity = CuentaMapper.domainToEntity(cuenta, cliente);
        cuentaRepository.save(cuentaEntity);
        return cuenta;
    }

    @Override
    public Cuenta leerById(Integer id) {
        CuentaEntity cuentaEntity = cuentaRepository.findById(id)
                .orElseThrow(() -> null);
        Cuenta cuenta = CuentaMapper.entityToDomain(cuentaEntity);
        return cuenta;
    }

    @Override
    public Cuenta eliminarById(Integer id) {
        CuentaEntity cuenta = cuentaRepository.findById(id).get();
        cuentaRepository.delete(cuenta);
        Cuenta cuentaEliminada = CuentaMapper.entityToDomain(cuenta);
        return cuentaEliminada;
    }

    @Override
    public boolean checkIfExist(Integer id) {
        return cuentaRepository.existsById(id);
    }
}
