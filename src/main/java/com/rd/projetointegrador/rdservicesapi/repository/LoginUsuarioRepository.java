package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

@Repository
public interface LoginUsuarioRepository extends JpaRepository<LoginUsuarioEntity, BigInteger> {

    //Optional<LoginUsuarioEntity> findByDsEmail(String email);

}
