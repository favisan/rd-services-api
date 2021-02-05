package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<AgendaEntity, BigInteger> {
    Optional<AgendaEntity> findByIdAgenda(BigInteger idAgenda);
}
