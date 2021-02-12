package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, BigInteger> {

    List<EnderecoEntity> findByDsEnderecoContainingOrDsBairroContaining(String dsEndereco, String dsBairro); // SELECT * FROM TB_ENDERECO WHERE DS_ENDERECO = ??

}
