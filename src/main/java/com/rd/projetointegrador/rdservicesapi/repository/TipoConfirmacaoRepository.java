package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.TipoConfirmacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TipoConfirmacaoRepository extends JpaRepository<TipoConfirmacaoEntity, BigInteger> {
    Optional<TipoConfirmacaoEntity> findByIdTipoConfirmacao(BigInteger idTipoConfirmacao);
}
