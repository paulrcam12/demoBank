package com.example.demobank.adapter.in;


import com.example.demobank.application.services.CuentaCRUDService;
import com.example.demobank.domain.Cuenta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CuentaCRUDController.class)
class CuentaCRUDControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CuentaCRUDService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerCuentaById() throws Exception {

        Cuenta cuenta = new Cuenta(478758, "Ahorro", 2000.0, true, 1001);

        String idCuentaEsperada = "478758";

        given(service.leer(cuenta.getNumeroCuenta())).willReturn(cuenta);


        mvc.perform(get("/api/v1/cuenta/obtener/"+idCuentaEsperada)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].numeroCuenta", is(cuenta.getNumeroCuenta())));

    }
    @Test
    void crearCuenta() throws Exception {
        Cuenta cuenta = new Cuenta(5051, "Ahorros", 2000.0, true, 1001);
        when(service.crear(any(Cuenta.class))).thenReturn(cuenta);
        mvc.perform(post("/api/v1/cuenta/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].numeroCuenta", is(cuenta.getNumeroCuenta())))
                .andDo(print());
    }

    @Test
    void puedeEditar() throws Exception {

        Cuenta cuenta = new Cuenta(47, "Ahorros", 2000.0, true, 1001);

        when(service.editar(any(Cuenta.class))).thenReturn(cuenta);

        mvc.perform(post("/api/v1/cuenta/editar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta))
                )
                .andExpect(status().isOk())
                .andDo(print());
        ;

    }
}