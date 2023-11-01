package com.example.demobank.adapter.out.persistence.Movimiento;

import com.example.demobank.adapter.out.persistence.Cuenta.CuentaEntity;
import com.example.demobank.adapter.out.persistence.Cuenta.CuentaMapper;
import com.example.demobank.adapter.out.persistence.Cuenta.CuentaRepository;
import com.example.demobank.application.port.out.Movimiento.*;
import com.example.demobank.domain.Cuenta;
import com.example.demobank.domain.Movimiento;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MovimientoPersistenceAdapter implements CrearMovimientoOutput, EditarMovimientoOutput, EliminarMovimientoOutput, LeerMovimientosOutput, CheckIfMovimientoExist {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoPersistenceAdapter(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {

        CuentaEntity cuentaEntity = cuentaRepository.findById(movimiento.getIdCuenta()).get();
        Cuenta cuenta = CuentaMapper.entityToDomain(cuentaEntity);

        MovimientoEntity movimientoEntity = MovimientoMapper.DomainToEntity(movimiento, cuenta, null);
        movimientoRepository.save(movimientoEntity);

        return movimiento;
    }

    @Override
    public Movimiento editar(Movimiento movimiento) {

        CuentaEntity cuentaEntity = cuentaRepository.findById(movimiento.getIdCuenta()).get();

        Cuenta cuenta = CuentaMapper.entityToDomain(cuentaEntity);

        MovimientoEntity movimientoEntity = MovimientoMapper.DomainToEntity(movimiento, cuenta, null);
        movimientoRepository.save(movimientoEntity);

        return movimiento;
    }

    @Override
    public Movimiento eliminarById(Integer id) {

        MovimientoEntity movimientoEntity = movimientoRepository.findById(id).get();

        movimientoRepository.delete(movimientoEntity);

        Movimiento movimiento = MovimientoMapper.entitytoDomain(movimientoEntity);
        return movimiento;
    }


    @Override
    public List<Movimiento> getAll() {

        List<MovimientoEntity> movimientos = movimientoRepository.findAll();

        List<Movimiento> movimientosList = new LinkedList<>();
        for (MovimientoEntity movimiento: movimientos) {
           movimientosList.add(MovimientoMapper.entitytoDomain(movimiento));
        }

        return movimientosList;
    }


    @Override
    public boolean checkIfExist(Integer id) {
        return movimientoRepository.existsById(id);
    }
}
