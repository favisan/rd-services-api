package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Contrato;
import com.rd.projetointegrador.rdservicesapi.dto.TipoUsuario;
import com.rd.projetointegrador.rdservicesapi.entity.ContratoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.TipoUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService {
    @Autowired
    private TipoUsuarioRepository repository;

    //MÉTODOS RETORNANDO A ENTITY
    public TipoUsuarioEntity getTipoUsuario(BigInteger idTipoUsuario) {
        Optional<TipoUsuarioEntity> optional = repository.findById(idTipoUsuario);
        return optional.get();

    }
    public List<TipoUsuarioEntity> getTiposUsuario(BigInteger idTipoUsuario) {
        return repository.findAll();
    }

    @Transactional
    public String cadastrarTipoUsuario(TipoUsuario tipoUsuario){

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setDsTipoUsuario(tipoUsuario.getDsTipoUsuario());

        repository.save(tipoUsuarioEntity);
        return "Tipo Usuário cadastrado com sucesso";
    }

    @Transactional
    public String alterarTipoUsuario(TipoUsuario tipoUsuario, BigInteger idTipoUsuario){

        TipoUsuarioEntity tipoUsuarioEntity =  getTipoUsuario(idTipoUsuario);
        tipoUsuarioEntity.setDsTipoUsuario(tipoUsuario.getDsTipoUsuario());

        repository.save(tipoUsuarioEntity);

        return "Tipo Usuário alterado com sucesso";

    }

    public String excluirTipoUsuario(BigInteger idTipoUsuario){
        repository.deleteById(idTipoUsuario);
        return "Exclusão do Tipo Usuário realizada com sucesso";

    }
}
