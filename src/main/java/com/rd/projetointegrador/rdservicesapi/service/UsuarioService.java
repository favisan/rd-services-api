package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Usuario;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    //TODO: O retorno precisa ser a DTO e não a Entity
    public UsuarioEntity getUsuario(BigInteger idUsuario) {
        System.out.println("IdUsuario: " + idUsuario);
        Optional<UsuarioEntity> optional = repository.findById(idUsuario);
        return optional.get();

    }

    //TODO: O retorno precisa ser a DTO e não a Entity
    public List<UsuarioEntity> getUsuarios() {
        return repository.findAll();

    }
    public List<UsuarioEntity> consultarPorNome(String nmUsuario) {
        return repository.findByNmUsuario(nmUsuario);
    }
    public List<UsuarioEntity> consultarPorCpf(String nrCpf){
        return repository.findByNrCpf(nrCpf);
    }

    //TODO: usar as entitdades das outras tabelas ao invés de biginteger ok
    //TODO: usar getById para procurar o registro nas tabelas dicionário
    //TODO: inserir entity encontrada na usuarioEntity
    @Transactional
    public String cadastrarUsuario(Usuario usuario){

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        GeneroEntity genero = generoRepository.findById(BigInteger.valueOf(1)).get();
        usuarioEntity.setGenero(genero);

        usuarioEntity.setIdEspMedica(usuario.getIdEspMedica());
        usuarioEntity.setIdUfCrm(usuario.getIdUfCrm());

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(1)).get();
        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

        usuarioEntity.setNmUsuario(usuario.getNmUsuario());
        usuarioEntity.setDtNascimento(usuario.getDtNascimento());
        usuarioEntity.setNrCpf(usuario.getNrCpf());
        usuarioEntity.setNrCrm(usuario.getNrCrm());
        usuarioEntity.setDsEndImg(usuario.getDsEndImg());

        repository.save(usuarioEntity);

        System.out.println(usuario.getIdUsuario() + " . " + usuario.getIdGenero() + " . " +usuario.getIdEspMedica() + " . " + usuario.getIdUfCrm()+ " . " + usuario.getIdTipoUsuario()+ " . " + usuario.getDtNascimento()+ " . " + usuario.getNrCpf()+ " . " + usuario.getNrCrm()+ " . " + usuario.getDsEndImg());

        return "Usuário cadastrado com sucesso";

    }


    //TODO: usar as entitdades das outras tabelas ao invés de biginteger OK
    //TODO: usar getById para procurar o registro nas tabelas dicionário
    //TODO: inserir entity encontrada na usuarioEntity
    @Transactional
    public String alterarUsuario(Usuario usuario, BigInteger idUsuario){

        UsuarioEntity usuarioEntity = getUsuario(idUsuario);

        GeneroEntity genero = generoRepository.findById(BigInteger.valueOf(1)).get();
        usuarioEntity.setGenero(genero);

        usuarioEntity.setIdEspMedica(usuario.getIdEspMedica());
        usuarioEntity.setIdUfCrm(usuario.getIdUfCrm());

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(1)).get();
        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

        usuarioEntity.setNmUsuario(usuario.getNmUsuario());
        usuarioEntity.setDtNascimento(usuario.getDtNascimento());
        usuarioEntity.setNrCpf(usuario.getNrCpf());
        usuarioEntity.setNrCrm(usuario.getNrCrm());
        usuarioEntity.setDsEndImg(usuario.getDsEndImg());

        usuarioEntity = repository.save(usuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //EXCLUSÃO OU BLOQUEIO????
    public String excluirUsuario(BigInteger idUsuario){
        repository.deleteById(idUsuario);
        return "Exclusão de usuário realizada com sucesso";

    }

}