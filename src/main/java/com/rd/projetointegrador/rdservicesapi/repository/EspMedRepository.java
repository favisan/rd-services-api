package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.EspMedEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface EspMedRepository extends JpaRepository<EspMedEntity, BigInteger> {
}
