package com.example.demobank.adapter.out.persistence.Movimiento;

import com.example.demobank.adapter.out.persistence.Cuenta.CuentaEntity;
import com.example.demobank.domain.Cliente;
import com.example.demobank.domain.Cuenta;
import com.example.demobank.domain.Movimiento;

public class MovimientoMapper {

    public static Movimiento entitytoDomain(MovimientoEntity movimientoEntity){
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(movimientoEntity.getIdMovimiento());
        movimiento.setFecha(movimientoEntity.getFecha());
        movimiento.setTipo(movimientoEntity.getTipo());
        movimiento.setValor(movimientoEntity.getValor());
        movimiento.setSaldo(movimientoEntity.getSaldo());

        movimiento.setIdCuenta(movimientoEntity.getCuenta().getNumeroCuenta());
        return movimiento;

    }

    public static  MovimientoEntity DomainToEntity(Movimiento movimiento, Cuenta cuenta, Cliente cliente){
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setIdMovimiento(movimiento.getIdMovimiento());
        movimientoEntity.setFecha(movimiento.getFecha());
        movimientoEntity.setTipo(movimiento.getTipo());
        movimientoEntity.setValor(movimiento.getValor());
        movimientoEntity.setSaldo(movimiento.getSaldo());

        CuentaEntity cuentaEntity = new CuentaEntity(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta(), cuenta.getSaldoInicial(), cuenta.isEstado(), null, null);

        movimientoEntity.setCuenta(cuentaEntity);
        return movimientoEntity;


    }
}
