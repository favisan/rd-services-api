package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.MedicacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MedicacaoRepository extends JpaRepository<MedicacaoEntity, BigInteger> {

}
