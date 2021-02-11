package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface CardapioRepository extends JpaRepository<CardapioEntity, BigInteger> {

    List<CardapioEntity> getByIdPaciente(BigInteger idPaciente);
}
