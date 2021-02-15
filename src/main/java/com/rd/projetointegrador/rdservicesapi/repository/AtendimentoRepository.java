package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, BigInteger> {

    //Grupo 4
    List<AtendimentoEntity> findByProntuario(ProntuarioEntity prontuario);
    List<AtendimentoEntity> findByPaciente(UsuarioEntity paciente);
    AtendimentoEntity findByAgPaciente(AgPacienteEntity agPacienteEntity);

    //Grupo3
     List<AtendimentoEntity> findByPacienteOrderByDtAtendimento(UsuarioEntity usuarioEntity);
     List<AtendimentoEntity>  findByMedicoOrderByDtAtendimentoDesc(UsuarioEntity medico);

}
