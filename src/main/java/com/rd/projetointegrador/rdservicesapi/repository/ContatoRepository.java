package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.ContatoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;


public interface ContatoRepository extends JpaRepository<ContatoEntity, BigInteger> {

    List<ContatoEntity> findByLoja(LojaEntity lojaEntity); // SELECT * FROM TB_CONTATO WHERE ID_LOJA = id

}
