package com.rd.projetointegrador.rdservicesapi.repository;
import com.rd.projetointegrador.rdservicesapi.entity.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;


public interface ReceitaRepository extends JpaRepository<ReceitaEntity, BigInteger> {

        @Query(
        value = " SELECT  R.* " +
                "    FROM TB_RECEITA R " +
                "    INNER JOIN TB_RECEITA_CATEGORIA RC ON (R.ID_RECEITA = RC.ID_RECEITA) " +
                "    INNER JOIN TB_CATEGORIA C ON  (RC.ID_CATEGORIA = C.ID_CATEGORIA) " +
                "    WHERE R.NM_NOME_RECEITA LIKE %:nomeReceita% OR C.DS_CATEGORIA LIKE %:categoria%",
        nativeQuery = true)
        List<ReceitaEntity> findByNomeReceita(@Param("nomeReceita") String nomeReceita,
                                              @Param("categoria") String categoria);


}
