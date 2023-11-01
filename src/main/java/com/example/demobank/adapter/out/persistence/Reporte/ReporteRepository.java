package com.example.demobank.adapter.out.persistence.Reporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<RowReporteEntity, Integer> {

    @Query(value = "SELECT movimiento.fecha,\n" +
            "persona.nombre,\n" +
            "cuenta.numero_cuenta, cuenta.tipo_cuenta, cuenta.saldo_inicial, cuenta.estado,\n" +
            "movimiento.valor, movimiento.saldo\n" +
            "FROM cuenta\n" +
            "INNER JOIN cliente\n" +
            "ON cuenta.id_cliente = cliente.cliente_id\n" +
            "INNER JOIN persona\n" +
            "ON persona.identificacion = cliente.id_persona\n" +
            "INNER JOIN movimiento\n" +
            "ON cuenta.numero_cuenta = movimiento.id_cuenta\n" +
            "WHERE cliente.cliente_id = ?3 \n" +
            "AND fecha BETWEEN ?1 AND ?2", nativeQuery = true)
    List<RowReporteEntity> getByClienteAndDates(String dateSince, String dateTo, Integer clienteId);

}
