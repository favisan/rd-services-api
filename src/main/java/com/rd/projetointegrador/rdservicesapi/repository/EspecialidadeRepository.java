package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface EspecialidadeRepository extends JpaRepository<EspMedEntity, BigInteger> {
}