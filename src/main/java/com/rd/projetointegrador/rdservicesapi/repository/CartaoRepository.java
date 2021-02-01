package com.rd.projetointegrador.rdservicesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CartaoRepository extends JpaRepository <CartaoRepository, BigInteger> {
}
