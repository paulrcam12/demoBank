package com.example.demobank.adapter.in;

import com.example.demobank.application.services.ClienteCRUDService;
import com.example.demobank.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteCRUDController.class)
//@ExtendWith(MockitoExtension.class)
class ClienteCRUDControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteCRUDService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    void crearCliente() throws Exception {
        Cliente cliente = new Cliente(1788775546,"Michael Corleone","Masculino",
                30,"Sicilia","0987225547",5001,"12345678",true);
        when(service.crearCliente(any(Cliente.class))).thenReturn(cliente);

        String payload = objectMapper.writeValueAsString(cliente);

        mvc.perform(post("/api/v1/cliente/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].clienteId", is(cliente.getClienteId())))
                .andDo(print());
    }
    @Test
    @Disabled
    void editarCliente() throws Exception {
        Cliente cliente = new Cliente(1788775546,"Michael Corleone","Masculino",
                30,"Sicilia","0987225547",5001,"12345678",true);
        when(service.editar(any(Cliente.class))).thenReturn(cliente);

        String payload = objectMapper.writeValueAsString(cliente);

        mvc.perform(post("/api/v1/cliente/editar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].clienteId", is(cliente.getClienteId())))
                .andDo(print());
    }

}