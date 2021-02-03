package com.rd.projetospring.primeiroprojeto.repository;

import com.rd.projetospring.primeiroprojeto.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LojaRepository extends JpaRepository<LojaEntity, BigInteger> {
}
