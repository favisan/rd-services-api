package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.entity.LembreteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LoginUsuarioRepository  extends JpaRepository<LoginUsuarioEntity, BigInteger> {

}
