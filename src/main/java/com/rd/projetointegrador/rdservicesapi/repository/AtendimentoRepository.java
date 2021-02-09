package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.dto.Prontuario;
import com.rd.projetointegrador.rdservicesapi.entity.AtendimentoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ProntuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, BigInteger> {

    List<AtendimentoEntity> findByProntuario(ProntuarioEntity prontuario);
    List<AtendimentoEntity> findByPaciente(UsuarioEntity paciente);

}
