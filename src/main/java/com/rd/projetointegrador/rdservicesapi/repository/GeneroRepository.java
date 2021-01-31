package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface GeneroRepository extends JpaRepository<GeneroEntity, BigInteger> {

}
