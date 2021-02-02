package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.TipoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public  interface  TipoPagamentoRepository extends JpaRepository<TipoPagamentoEntity , BigInteger> {
}
