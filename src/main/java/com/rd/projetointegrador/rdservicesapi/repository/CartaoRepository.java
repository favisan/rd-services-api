package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.CartaoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface CartaoRepository extends JpaRepository <CartaoEntity, BigInteger> {
    //GRUPO1

    List<CartaoEntity> findByUsuario(UsuarioEntity usuarioEntity);
}

