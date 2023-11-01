package com.example.demobank.adapter.out.persistence.Reporte;

import com.example.demobank.domain.RowReporte;
import com.mysql.cj.result.Row;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReporteRepositoryTest {

    @Autowired
    private ReporteRepository underTest;

    @Test
    @Disabled
    void getByClienteAndDates() throws ParseException {
        String fechaStr1 = "2023-10-31 03:57:09.001000";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Date fechaRow1 = formato.parse(fechaStr1);

        // given
        List<RowReporteEntity> reporteList = new LinkedList<>();
        RowReporteEntity row1 = new RowReporteEntity();
        row1.setFecha(fechaRow1);
        row1.setNombre("Marianela Montalvo");
        row1.setNumero_cuenta(225487);
        row1.setTipo_cuenta("Corriente");
        row1.setSaldo_inicial(100.0);
        row1.setEstado(true);
        row1.setValor(600.0);
        row1.setSaldo(700.0);

        reporteList.add(row1);

        // when

        List<RowReporteEntity> reporteTest = underTest.getByClienteAndDates("2023-10-31 00:00:00", "2023-10-31 03:58:09.001000", 1002);
        // then

        assertThat(reporteTest.get(0).getNombre()).isEqualTo(reporteList.get(0).getNombre());


    }
}