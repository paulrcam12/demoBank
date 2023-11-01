package com.example.demobank.application.port.in;

import com.example.demobank.domain.RowReporte;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface GenerarReportePort {
    List<RowReporte> get(String dateSince, String dateTo, Integer clienteId);
}
