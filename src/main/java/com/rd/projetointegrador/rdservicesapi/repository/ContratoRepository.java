package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ContratoRepository extends JpaRepository<ContratoEntity, BigInteger> {

    public List<ContratoEntity> findByIdUsuarioOrderByDtVigencia(BigInteger idUsuario);

    public List<ContratoEntity> findByIdUsuario(BigInteger idUsuario);
}
