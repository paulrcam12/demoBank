package com.example.demobank.adapter.out.persistence.Reporte;

import com.example.demobank.domain.RowReporte;

public class RowReporteMapper {
    public static RowReporte entityToDomain(RowReporteEntity rowReporteEntity){
        RowReporte rowReporte = new RowReporte();
        rowReporte.setFecha(rowReporteEntity.getFecha());
        rowReporte.setNombre(rowReporteEntity.getNombre());
        rowReporte.setNumero_cuenta(rowReporteEntity.getNumero_cuenta());
        rowReporte.setTipo_cuenta(rowReporteEntity.getTipo_cuenta());
        rowReporte.setSaldo_inicial(rowReporteEntity.getSaldo_inicial());
        rowReporte.setEstado(rowReporteEntity.isEstado());
        rowReporte.setValor(rowReporteEntity.getValor());
        rowReporte.setSaldo(rowReporteEntity.getSaldo());

        return rowReporte;
    }
//    public static RowReporteEntity DomainToEntity(RowReporte rowReporte){
//        RowReporteEntity rowReporteEntity = new RowReporteEntity();
//        rowReporteEntity.setFecha(rowReporte.getFecha());
//        rowReporteEntity.setCliente(rowReporte.getCliente());
//        rowReporteEntity.setNumero_cuenta(rowReporte.getNumero_cuenta());
//        rowReporteEntity.setTipoDeCuenta(rowReporte.getTipoDeCuenta());
//        rowReporteEntity.setSaldoInicial(rowReporte.getSaldoInicial());
//        rowReporteEntity.setEstado(rowReporte.isEstado());
//        rowReporteEntity.setMovimiento(rowReporte.getMovimiento());
//        rowReporteEntity.setSaldoDisponible(rowReporte.getSaldoDisponible());
//
//        return rowReporteEntity;
//    }

}
