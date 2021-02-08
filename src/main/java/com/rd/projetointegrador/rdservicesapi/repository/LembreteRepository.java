package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface LembreteRepository extends JpaRepository<LembreteEntity, BigInteger> {

    List<LembreteEntity> findByIdPaciente(BigInteger idPaciente);
    List<LembreteEntity> findByIdPacienteOrderByDtLembreteAsc(BigInteger idPaciente);
    List<LembreteEntity> findByIdPacienteOrderByDtLembreteDesc(BigInteger idPaciente);
    List<LembreteEntity> findByIdPacienteOrderByDtCriacao(BigInteger idPaciente);

}
