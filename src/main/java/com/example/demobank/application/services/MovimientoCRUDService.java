package com.example.demobank.application.services;

import com.example.demobank.application.port.in.Movimiento.CrearMovimientoInput;
import com.example.demobank.application.port.in.Movimiento.EditarMovimientoInput;
import com.example.demobank.application.port.in.Movimiento.EliminarMovimientoInput;
import com.example.demobank.application.port.in.Movimiento.LeerMovimientosInput;
import com.example.demobank.application.port.out.Cuenta.LeerCuentaOutput;
import com.example.demobank.application.port.out.Movimiento.*;
import com.example.demobank.domain.Cuenta;
import com.example.demobank.domain.Movimiento;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoCRUDService implements CrearMovimientoInput, EditarMovimientoInput, EliminarMovimientoInput, LeerMovimientosInput {

    private final CrearMovimientoOutput crearMovimientoOutput;
    private final EditarMovimientoOutput editarMovimientoOutput;
    private final EliminarMovimientoOutput eliminarMovimientoOutput;
    private final LeerMovimientosOutput leerMovimientosOutput;

    private final LeerCuentaOutput leerCuentaOutput;
    private final CheckIfMovimientoExist checkIfMovimientoExist;

    public MovimientoCRUDService(CrearMovimientoOutput crearMovimientoOutput,
                                 EditarMovimientoOutput editarMovimientoOutput,
                                 EliminarMovimientoOutput eliminarMovimientoOutput,
                                 LeerMovimientosOutput leerMovimientosOutput,
                                 LeerCuentaOutput leerCuentaOutput,
                                 CheckIfMovimientoExist checkIfMovimientoExist
    ) {
        this.crearMovimientoOutput = crearMovimientoOutput;
        this.editarMovimientoOutput = editarMovimientoOutput;
        this.eliminarMovimientoOutput = eliminarMovimientoOutput;
        this.leerMovimientosOutput = leerMovimientosOutput;
        this.leerCuentaOutput = leerCuentaOutput;
        this.checkIfMovimientoExist = checkIfMovimientoExist;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {

        Cuenta cuenta = leerCuentaOutput.leerById(movimiento.getIdCuenta());
        if (cuenta == null){
            System.out.println("Cuenta del "+movimiento.getIdCuenta()+" no encontrada.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cuenta del "+movimiento.getIdCuenta()+" no encontrada.");
        }

        String tipoMovimiento = movimiento.getTipo();
        Double valor = movimiento.getValor();
        Double saldoInicial = cuenta.getSaldoInicial();
        movimiento.setIdMovimiento(0);

        if(tipoMovimiento.equals("CREDITO")){
            movimiento.setSaldo(saldoInicial+valor);

        }else if (tipoMovimiento.equals("DEBITO")){
            if(valor <= saldoInicial) {
                movimiento.setValor(valor*-1);
                movimiento.setSaldo(saldoInicial-valor);
            }else {
                System.out.println("Saldo insuficiente para la transacción");
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Saldo insuficiente para la transacción");
            }
        }else{
            System.out.println("Tipo de movimiento no permitido, debería ser \"CREDITO\" o \"DEBITO\".");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tipo de movimiento no permitido, debería ser \"CREDITO\" o \"DEBITO\".");
        }
        movimiento.setFecha(new Date());

        return crearMovimientoOutput.crear(movimiento);
    }

    @Override
    public Movimiento editar(Movimiento movimiento) {
        if(!checkIfMovimientoExist.checkIfExist(movimiento.getIdMovimiento())){
            System.out.println("Movimiento con el id  "+movimiento.getIdMovimiento()+" no encontrado.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movimiento con el id "+movimiento.getIdMovimiento()+" no encontrado.");
        }
        return editarMovimientoOutput.editar(movimiento);
    }

    @Override
    public Movimiento eliminarById(Integer id) {
        if(!checkIfMovimientoExist.checkIfExist(id)){
            System.out.println("Movimiento con el id  "+id+" no encontrado.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movimiento con el id  "+id+" no encontrado.");
        }
        return eliminarMovimientoOutput.eliminarById(id);
    }

    @Override
    public List<Movimiento> getAll() {
        return leerMovimientosOutput.getAll();
    }
}
