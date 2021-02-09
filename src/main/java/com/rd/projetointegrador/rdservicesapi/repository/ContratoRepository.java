package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ContratoRepository extends JpaRepository<ContratoEntity, BigInteger> {
    //GRUPO1

    public List<ContratoEntity> findByUsuarioOrderByDtVigencia(UsuarioEntity usuarioEntity);
    public List<ContratoEntity> findByUsuario(UsuarioEntity usuarioEntity);
}

