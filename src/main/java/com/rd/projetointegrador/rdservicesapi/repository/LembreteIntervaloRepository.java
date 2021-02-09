package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteIntervaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LembreteIntervaloRepository extends JpaRepository<LembreteIntervaloEntity, BigInteger> {
    //GRUPO1
}
