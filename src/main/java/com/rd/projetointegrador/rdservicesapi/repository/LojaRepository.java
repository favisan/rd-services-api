package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface LojaRepository extends JpaRepository<LojaEntity, BigInteger> {

    @Query(
            value = "SELECT L.* " +
                    " FROM TB_LOJA L " +
                    " INNER JOIN TB_LOJA_ENDERECO LE ON (L.ID_LOJA = LE.ID_LOJA) " +
                    " INNER JOIN TB_ENDERECO E ON  (LE.ID_ENDERECO = E.ID_ENDERECO) " +
                    " INNER JOIN TB_CIDADE C ON (E.ID_CIDADE = C.ID_CIDADE) " +
                    " INNER JOIN TB_UF U ON (C.ID_UF = U.ID_UF) " +
                    " WHERE E.DS_BAIRRO LIKE %:local1% OR C.DS_CIDADE LIKE %:local2% OR E.DS_COMPLEMENTO LIKE %:local3% ",
            nativeQuery = true)
    List<LojaEntity> getLojasPorLocalidade(@Param("local1") String local1,
                                           @Param("local2") String local2,
                                           @Param("local3") String local3);
}
