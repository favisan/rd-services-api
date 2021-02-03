package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ServicoRepository extends JpaRepository<ServicoEntity, BigInteger> {

    List<ServicoEntity> findByNome(String nome); // SELECT * FROM TB_SERVICO WHERE DS_SERVICO = ???

    //List<ServicoEntity> findById(long id); // SELECT * FROM TB_USUARIO WHERE NR_CPF = ?



}
