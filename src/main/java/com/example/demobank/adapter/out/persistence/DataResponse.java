package com.example.demobank.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private String  id;
    private String accion;
    private String mensaje;
    private List data;
}
