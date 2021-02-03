package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface CartaoRepository extends JpaRepository <CartaoEntity, BigInteger> {

    List<CartaoEntity> findByIdUsuario(BigInteger IdUsuario);
}
