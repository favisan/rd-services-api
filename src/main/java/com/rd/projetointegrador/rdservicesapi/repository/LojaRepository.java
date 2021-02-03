package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface LojaRepository extends JpaRepository<LojaEntity, BigInteger> {
}
