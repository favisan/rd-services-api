package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.CardapioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoRefeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface TipoRefeRepository extends JpaRepository<TipoRefeEntity, BigInteger> {


    //List<CardapioEntity> findByIdTipo (CardapioEntity idTipoRefeicao);
}
