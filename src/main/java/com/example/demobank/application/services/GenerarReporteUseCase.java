package com.example.demobank.application.services;

import com.example.demobank.application.port.in.GenerarReportePort;
import com.example.demobank.application.port.out.ReadDataReportePort;
import com.example.demobank.domain.RowReporte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerarReporteUseCase implements GenerarReportePort {

    private final ReadDataReportePort readDataReportePort;

    public GenerarReporteUseCase(ReadDataReportePort readDataReportePort) {
        this.readDataReportePort = readDataReportePort;
    }


    @Override
    public List<RowReporte> get(String dateSince, String dateTo, Integer clienteId) {
        //validation si la fecha to es superior a la since

        List<RowReporte> reporteList = readDataReportePort.getData(dateSince, dateTo, clienteId);
        // validation
        return reporteList;
    }
}
