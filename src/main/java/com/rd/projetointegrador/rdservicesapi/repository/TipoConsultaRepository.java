package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.TipoConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface TipoConsultaRepository extends JpaRepository <TipoConsultaEntity, BigInteger>{


}
