package com.rd.projetointegrador.rdservicesapi.repository;

import com.rd.projetointegrador.rdservicesapi.dto.OutputMedico;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UsuarioG4Repository extends JpaRepository<UsuarioEntity, BigInteger> {

    UsuarioEntity findByNrCpf(String cpf);

    OutputMedico findByIdUsuario(BigInteger idUsuario);

}
