package com.example.demobank.adapter.out.persistence.Reporte;

import com.example.demobank.application.port.out.ReadDataReportePort;
import com.example.demobank.domain.RowReporte;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ReportPersistenceAdapter implements ReadDataReportePort {
    private final ReporteRepository reporteRepository;

    public ReportPersistenceAdapter(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Override
    public List<RowReporte> getData(String dateSince, String dateTo, Integer clienteId) {
        List<RowReporteEntity> reporteEList = reporteRepository.getByClienteAndDates(dateSince, dateTo, clienteId);
        List<RowReporte> reporteList = new LinkedList<>();
        for (RowReporteEntity rowReporteEntity: reporteEList) {
            reporteList.add(RowReporteMapper.entityToDomain(rowReporteEntity));
        }
        return reporteList;
    }
}
