package com.rd.projetospring.primeiroprojeto.repository;

import com.rd.projetospring.primeiroprojeto.entity.ContatoEntity;
import com.rd.projetospring.primeiroprojeto.entity.EnderecoEntity;
import com.rd.projetospring.primeiroprojeto.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, BigInteger> {

    List<EnderecoEntity> findByDsEnderecoContainingOrDsBairroContaining(String dsEndereco, String dsBairro); // SELECT * FROM TB_ENDERECO WHERE DS_ENDERECO = ??

    //SELECT * FROM TB_LOJA tl WHERE tl.NM_LOJA LIKE '%o%'
}
