package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LembreteRepository extends JpaRepository<LembreteEntity, BigInteger> {
}
