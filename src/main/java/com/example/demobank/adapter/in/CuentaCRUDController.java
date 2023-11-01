package com.example.demobank.adapter.in;

import com.example.demobank.adapter.out.persistence.DataResponse;
import com.example.demobank.application.port.in.Cuenta.CrearCuentaInput;
import com.example.demobank.application.port.in.Cuenta.EditarCuentaInput;
import com.example.demobank.application.port.in.Cuenta.EliminarCuentaInput;
import com.example.demobank.application.port.in.Cuenta.ObtenerCuentaInput;
import com.example.demobank.domain.Cuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cuenta")
public class CuentaCRUDController {

    private final CrearCuentaInput crearCuentaInput;
    private final EditarCuentaInput editarCuentaInput;
    private final ObtenerCuentaInput obtenerCuentaInput;
    private final EliminarCuentaInput eliminarCuentaInput;

    public CuentaCRUDController(CrearCuentaInput crearCuentaInput, EditarCuentaInput editarCuentaInput, ObtenerCuentaInput obtenerCuentaInput, EliminarCuentaInput eliminarCuentaInput) {
        this.crearCuentaInput = crearCuentaInput;
        this.editarCuentaInput = editarCuentaInput;
        this.obtenerCuentaInput = obtenerCuentaInput;
        this.eliminarCuentaInput = eliminarCuentaInput;
    }

    @PostMapping("/crear")
    ResponseEntity<DataResponse> crearCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaCreada = crearCuentaInput.crear(cuenta);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("guardar");

        if (cuentaCreada == null){
            System.out.println("Error creando cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creando cuenta");
        }
        dataResponse.setId(String.valueOf(cuentaCreada.getNumeroCuenta()));
        dataResponse.setMensaje("Cuenta creada exitosamente");
        dataResponse.setData(List.of(cuentaCreada));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/editar")
    ResponseEntity<DataResponse> editarCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaEditada = editarCuentaInput.editar(cuenta);
        if (cuentaEditada == null){
            System.out.println("Error editando cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error editando cuenta");
        }
        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("editar");
        dataResponse.setId(String.valueOf(cuentaEditada.getNumeroCuenta()));
        dataResponse.setMensaje("Cuenta editada exitosamente");
        dataResponse.setData(List.of(cuentaEditada));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping(path ="obtener/{cuentaId}")
    ResponseEntity<DataResponse> obtenerCuentaById(@PathVariable Integer cuentaId){
        Cuenta cuenta = obtenerCuentaInput.leer(cuentaId);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("obtener");

        if (cuenta == null){
            System.out.println("Error obteniendo cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error obteniendo cuenta");
        }

        dataResponse.setId(String.valueOf(cuenta.getNumeroCuenta()));
        dataResponse.setMensaje("Cuenta encontrada");
        dataResponse.setData(List.of(cuenta));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "eliminar/{cuentaId}")
    ResponseEntity<DataResponse> eliminarCuentaById(@PathVariable Integer cuentaId) {
        Cuenta cuentaEliminada = eliminarCuentaInput.eliminar(cuentaId);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("eliminar");

        if (cuentaEliminada == null){
            dataResponse.setId(String.valueOf(cuentaId));
            System.out.println("Error eliminando cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error eliminando cuenta");
        }
        dataResponse.setId(String.valueOf(cuentaEliminada.getNumeroCuenta()));
        dataResponse.setMensaje("Cuenta eliminada");
        dataResponse.setData(List.of(cuentaEliminada));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }


}
