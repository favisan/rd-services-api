package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ContratoRepository extends JpaRepository<ContratoEntity, BigInteger> {
}
