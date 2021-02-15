package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, BigInteger> {
    //GRUPO1
}
