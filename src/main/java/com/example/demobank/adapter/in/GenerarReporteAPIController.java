package com.example.demobank.adapter.in;

import com.example.demobank.application.port.in.GenerarReportePort;
import com.example.demobank.domain.RowReporte;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reporte")
public class GenerarReporteAPIController {

    private final GenerarReportePort generarReportePort;

    public GenerarReporteAPIController(GenerarReportePort generarReportePort) {
        this.generarReportePort = generarReportePort;
    }

    @GetMapping(path ="/generar")
    ResponseEntity<List<RowReporte>> generar(@RequestParam String since, @RequestParam String to, @RequestParam Integer clienteId){
       List<RowReporte> reporte = generarReportePort.get(since, to, clienteId);
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }
}
