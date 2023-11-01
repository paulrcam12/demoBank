package com.example.demobank.adapter.out.persistence.Cuenta;

import com.example.demobank.adapter.out.persistence.Cliente.ClienteEntity;
import com.example.demobank.adapter.out.persistence.Cliente.ClienteMapper;
import com.example.demobank.domain.Cliente;
import com.example.demobank.domain.Cuenta;

public class CuentaMapper {

    public static Cuenta entityToDomain(CuentaEntity cuentaEntity){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaEntity.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaEntity.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaEntity.getSaldoInicial());
        cuenta.setEstado(cuentaEntity.isEstado());
        cuenta.setClienteId(cuentaEntity.getCliente().getClienteId());

        return cuenta;
    }

    public static CuentaEntity domainToEntity(Cuenta cuenta, Cliente cliente){
        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaEntity.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaEntity.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaEntity.setEstado(cuenta.isEstado());

        ClienteEntity clienteEntity = ClienteMapper.domainToEntity(cliente);
        cuentaEntity.setCliente(clienteEntity);
        return cuentaEntity;
    }

}
