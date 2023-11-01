package com.example.demobank.application.services;

import com.example.demobank.application.port.in.Cuenta.CrearCuentaInput;
import com.example.demobank.application.port.in.Cuenta.EditarCuentaInput;
import com.example.demobank.application.port.in.Cuenta.EliminarCuentaInput;
import com.example.demobank.application.port.in.Cuenta.ObtenerCuentaInput;
import com.example.demobank.application.port.out.Cliente.CheckIfClienteExist;
import com.example.demobank.application.port.out.Cuenta.*;
import com.example.demobank.domain.Cuenta;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuentaCRUDService implements CrearCuentaInput, EditarCuentaInput, EliminarCuentaInput, ObtenerCuentaInput {

    private final CrearCuentaOutput crearCuentaOutput;
    private final LeerCuentaOutput leerCuentaOutput;
    private final ActualizarCuentaOutput actualizarCuentaOutput;
    private final EliminarCuentaOutput eliminarCuentaOutput;
    private final CheckIfCuentaExist checkIfCuentaExist;
    private final CheckIfClienteExist checkIfClienteExist;

    public CuentaCRUDService(CrearCuentaOutput crearCuentaOutput,
                             LeerCuentaOutput leerCuentaOutput,
                             ActualizarCuentaOutput actualizarCuentaOutput,
                             EliminarCuentaOutput eliminarCuentaOutput,
                             CheckIfCuentaExist checkIfCuentaExist,
                             CheckIfClienteExist checkIfClienteExist) {
        this.crearCuentaOutput = crearCuentaOutput;
        this.leerCuentaOutput = leerCuentaOutput;
        this.actualizarCuentaOutput = actualizarCuentaOutput;
        this.eliminarCuentaOutput = eliminarCuentaOutput;
        this.checkIfCuentaExist = checkIfCuentaExist;
        this.checkIfClienteExist = checkIfClienteExist;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        boolean exist = checkIfCuentaExist.checkIfExist(cuenta.getNumeroCuenta());
        if (exist){
            System.out.println("La cuenta con id "+cuenta.getNumeroCuenta()+" ya existe.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La cuenta con id "+cuenta.getNumeroCuenta()+" ya existe.");
        }
        boolean exist2 = checkIfClienteExist.checkIfExist(cuenta.getClienteId());
        if(!exist2){
            System.out.println("El cliente con id "+cuenta.getClienteId()+" no existe.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente con id "+cuenta.getClienteId()+" no existe.");
        }
        return crearCuentaOutput.guardar(cuenta);


    }

    @Override
    public Cuenta editar(Cuenta cuenta) {
        boolean exist = checkIfCuentaExist.checkIfExist(cuenta.getNumeroCuenta());
        if (!exist){
            System.out.println("La cuenta con id "+cuenta.getNumeroCuenta()+" no existe.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La cuenta con id "+cuenta.getNumeroCuenta()+" no existe.");
        }
        boolean exist2 = checkIfClienteExist.checkIfExist(cuenta.getClienteId());
        if(!exist2){
            System.out.println("El cliente con id "+cuenta.getClienteId()+" no existe.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente con id "+cuenta.getClienteId()+" no existe.");
        }
        return actualizarCuentaOutput.editar(cuenta);
    }

    @Override
    public Cuenta eliminar(Integer id) {
        boolean exist = checkIfCuentaExist.checkIfExist(id);
        if (!exist){
            System.out.println("La cuenta con id "+id+" no existe.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La cuenta con id "+id+" no existe.");
        }
        return eliminarCuentaOutput.eliminarById(id);
    }

    @Override
    public Cuenta leer(Integer id) {
        return leerCuentaOutput.leerById(id);
    }
}
