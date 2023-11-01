package com.example.demobank.application.port.out;

import com.example.demobank.domain.RowReporte;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReadDataReportePort {
    List<RowReporte> getData(String dateSince, String dateTo, Integer clienteId);
}
