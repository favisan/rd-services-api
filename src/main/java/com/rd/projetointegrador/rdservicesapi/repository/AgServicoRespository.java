package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface AgServicoRespository extends JpaRepository<AgServicoEntity, BigInteger> {

    List<AgServicoEntity> findByIdLojaAndDtDataHoraBetween(LojaEntity loja, Date fromDate, Date toDate);

    //SELECT * FROM TB_AG_SERVICO WHERE ID_LOJA = 9 AND DT_DATA_HORA BETWEEN '2021-02-02 00:00:01' AND '2021-02-02 23:59:59'
    //SELECT HORA FROM TB_AG_SERVICO WHERE ID_LOJA = ?? AND DATA = ??: LISTA COM HORÁRIOS INDISPONÍVEIS


}
