package com.example.demobank.adapter.in;

import com.example.demobank.adapter.out.persistence.DataResponse;
import com.example.demobank.application.port.in.Cliente.CrearClienteInput;
import com.example.demobank.application.port.in.Cliente.EditarClienteInput;
import com.example.demobank.application.port.in.Cliente.EliminarClienteInput;
import com.example.demobank.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cliente")
public class ClienteCRUDController {

    private final CrearClienteInput crearClienteInput;
    private final EditarClienteInput editarClienteInput;
    private final EliminarClienteInput eliminarClienteInput;

    public ClienteCRUDController(CrearClienteInput crearClienteInput, EditarClienteInput editarClienteInput, EliminarClienteInput eliminarClienteInput) {
        this.crearClienteInput = crearClienteInput;
        this.editarClienteInput = editarClienteInput;
        this.eliminarClienteInput = eliminarClienteInput;
    }

    @PostMapping("/crear")
    ResponseEntity<DataResponse> crearCliente(@RequestBody Cliente cliente){

        Cliente clienteCreado = crearClienteInput.crearCliente(cliente);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("guardar");

        dataResponse.setId(String.valueOf(clienteCreado.getClienteId()));
        dataResponse.setMensaje("Cliente creado exitosamente");
        dataResponse.setData(List.of(clienteCreado));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);


    }

    @PostMapping("/editar")
    ResponseEntity<DataResponse> editarCliente(@RequestBody Cliente cliente){
        Cliente clienteEditado = editarClienteInput.editar(cliente);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("editar");

        if (clienteEditado == null){
            dataResponse.setId(String.valueOf(cliente.getClienteId()));
            dataResponse.setMensaje("Cliente no encontrado");
            return new ResponseEntity<>(dataResponse, HttpStatus.NOT_FOUND);
        }
        //Agregar casos cuando se envia el mismo valor
        dataResponse.setId(String.valueOf(clienteEditado.getClienteId()));
        dataResponse.setMensaje("Cliente editado exitosamente");
        dataResponse.setData(List.of(clienteEditado));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }


    @DeleteMapping(path = "eliminar/{clienteId}")
    ResponseEntity<DataResponse> eliminarCliente(@PathVariable Integer clienteId){
        Cliente clienteEliminado = eliminarClienteInput.eliminarById(clienteId);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setAccion("eliminar");

        if (clienteEliminado == null){
            dataResponse.setId(String.valueOf(clienteId));
            dataResponse.setMensaje("Cliente no encontrado");
            return new ResponseEntity<>(dataResponse, HttpStatus.NOT_FOUND);
        }
        //Agregar casos cuando se envia el mismo valor
        dataResponse.setId(String.valueOf(clienteEliminado.getClienteId()));
        dataResponse.setMensaje("Cliente eliminado exitosamente");
        dataResponse.setData(List.of(clienteEliminado));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
