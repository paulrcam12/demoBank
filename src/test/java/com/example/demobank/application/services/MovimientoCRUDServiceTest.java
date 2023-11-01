package com.example.demobank.application.services;

import com.example.demobank.application.port.out.Cuenta.LeerCuentaOutput;
import com.example.demobank.application.port.out.Movimiento.*;
import com.example.demobank.domain.Cuenta;
import com.example.demobank.domain.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovimientoCRUDServiceTest {

    private MovimientoCRUDService underTest;

    @Mock private CrearMovimientoOutput crearMovimientoOutput;
    @Mock private EditarMovimientoOutput editarMovimientoOutput;
    @Mock private EliminarMovimientoOutput eliminarMovimientoOutput;
    @Mock private LeerMovimientosOutput leerMovimientosOutput;
    @Mock private LeerCuentaOutput leerCuentaOutput;
    private CheckIfMovimientoExist checkIfMovimientoExist;
    private Movimiento movimiento;

    @BeforeEach
    void setUp() throws ParseException {
        underTest = new MovimientoCRUDService(crearMovimientoOutput, editarMovimientoOutput, eliminarMovimientoOutput, leerMovimientosOutput, leerCuentaOutput, checkIfMovimientoExist);
        String fechaStr1 = "2023-10-31 03:56:26.121000";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Date fecha = formato.parse(fechaStr1);

        // given
         movimiento = new Movimiento(5, fecha, "DEBITO", 575.0, 1425.0, 478758);
    }


    @Test
    void canGetAllMovimientos() {
        //when
        underTest.getAll();

        verify(leerMovimientosOutput).getAll();
    }

    @Test
    void canRegisterNewMovimiento() {

        Cuenta cuentaEsperada = new Cuenta(478758, "Ahorro", 2000.0, true, 1001);

        given(leerCuentaOutput.leerById(movimiento.getIdCuenta()))
                .willReturn(cuentaEsperada);

        // when
        underTest.crear(movimiento);

        //then
        ArgumentCaptor<Movimiento> movimientoArgumentCaptor =
                ArgumentCaptor.forClass(Movimiento.class);
        verify(crearMovimientoOutput)
                .crear(movimientoArgumentCaptor.capture());
        Movimiento capturedMovimiento = movimientoArgumentCaptor.getValue();

        assertThat(capturedMovimiento).isEqualTo(movimiento);

    }

    @Test
    void canNotFindCuentaParaMovimiento() {

        // given
        given(leerCuentaOutput.leerById(movimiento.getIdCuenta()))
                .willReturn(null);
        // when
        //then
        assertThatThrownBy(() -> underTest.crear(movimiento))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Cuenta del "+movimiento.getIdCuenta()+" no encontrada.");

    }

    @Test
    void mustSaldoInsuficiente() {


        Cuenta cuentaEsperada = new Cuenta(478758, "Ahorro", 20.0, true, 1001);

        given(leerCuentaOutput.leerById(movimiento.getIdCuenta()))
                .willReturn(cuentaEsperada);

        // when
        //then
        assertThatThrownBy(() -> underTest.crear(movimiento))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Saldo insuficiente para la transacción");

    }

}