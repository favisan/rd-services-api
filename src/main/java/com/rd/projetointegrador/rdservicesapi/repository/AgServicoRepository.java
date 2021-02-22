package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.AgServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface AgServicoRepository extends JpaRepository<AgServicoEntity, BigInteger> {

    List<AgServicoEntity> findByIdLojaAndDtDataHoraBetweenAndIdStatus(LojaEntity loja, Date fromDate, Date toDate, StatusEntity status);

    @Modifying
    @Query("UPDATE AgServicoEntity c SET c.idStatus = :status WHERE c.idAgendamento = :id")
    int updateIdStatus(BigInteger id, StatusEntity status);

}
