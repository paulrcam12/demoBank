package com.example.demobank.adapter.out.persistence.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository  extends JpaRepository<CuentaEntity, Integer> {
}
