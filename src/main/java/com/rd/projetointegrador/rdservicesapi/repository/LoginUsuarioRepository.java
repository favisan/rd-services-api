package com.rd.projetointegrador.rdservicesapi.repository;


import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface LoginUsuarioRepository  extends JpaRepository<LoginUsuarioEntity, BigInteger> {
    //GRUPO1

    public LoginUsuarioEntity findOneByIdUsuario(BigInteger idUsuario);
    public LoginUsuarioEntity findByDsEmail(String dsEmail);
}
