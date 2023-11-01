package com.example.demobank.adapter.in;

import com.example.demobank.adapter.out.persistence.DataResponse;
import com.example.demobank.application.port.in.Movimiento.CrearMovimientoInput;
import com.example.demobank.application.port.in.Movimiento.EditarMovimientoInput;
import com.example.demobank.application.port.in.Movimiento.EliminarMovimientoInput;
import com.example.demobank.domain.Movimiento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movimiento")
public class MovimientoCRUDController {

    private final CrearMovimientoInput crearMovimientoInput;
    private final EditarMovimientoInput editarMovimientoInput;
    private final EliminarMovimientoInput eliminarMovimientoInput;

    public MovimientoCRUDController(CrearMovimientoInput crearMovimientoInput, EditarMovimientoInput editarMovimientoInput, EliminarMovimientoInput eliminarMovimientoInput) {
        this.crearMovimientoInput = crearMovimientoInput;
        this.editarMovimientoInput = editarMovimientoInput;
        this.eliminarMovimientoInput = eliminarMovimientoInput;
    }

    @PostMapping("/crear")
    ResponseEntity<DataResponse> crearMovimiento(@RequestBody Movimiento movimiento){

        Movimiento movimientoCreado = crearMovimientoInput.crear(movimiento);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("guardar");

        if (movimientoCreado == null){
            dataResponse.setMensaje("Error en transferencia.");
            return new ResponseEntity<>(dataResponse, HttpStatus.CONFLICT);
        }

        dataResponse.setId(String.valueOf(movimientoCreado.getIdMovimiento()));
        dataResponse.setMensaje("Movimiento creado exitosamente");
        dataResponse.setData(List.of(movimientoCreado));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/editar")
    ResponseEntity<DataResponse> editarMovimiento(@RequestBody Movimiento movimiento){

        Movimiento movimientoEditado = editarMovimientoInput.editar(movimiento);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("editar");

        if (movimientoEditado == null){
            dataResponse.setMensaje("Error en transferencia.");
            return new ResponseEntity<>(dataResponse, HttpStatus.CONFLICT);
        }

        dataResponse.setId(String.valueOf(movimientoEditado.getIdMovimiento()));
        dataResponse.setMensaje("Movimiento editado exitosamente");
        dataResponse.setData(List.of(movimientoEditado));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }


    @DeleteMapping(path = "eliminar/{movimientoId}")
    ResponseEntity<DataResponse> eliminarMovimiento(@PathVariable Integer movimientoId){

        Movimiento movimientoEliminado = eliminarMovimientoInput.eliminarById(movimientoId);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("eliminar");

        if (movimientoEliminado == null){
            dataResponse.setMensaje("Error .");
            return new ResponseEntity<>(dataResponse, HttpStatus.CONFLICT);
        }

        //Agregar casos cuando se envia el mismo valor
        dataResponse.setId(String.valueOf(movimientoEliminado.getIdMovimiento()));
        dataResponse.setMensaje("Movimiento eliminado exitosamente");
        dataResponse.setData(List.of(movimientoEliminado));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
