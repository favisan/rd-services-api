package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.LoginUsuario;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.LoginUsuarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class LoginUsuarioService {

    @Autowired private LoginUsuarioRepository repository;
    @Autowired private UsuarioRepository usuarioRepository;

    //MÉTODO: conversão de DTO para Entity
    public LoginUsuarioEntity conversaoLoginUsuarioEntity(LoginUsuario loginUsuario, LoginUsuarioEntity loginUsuarioEntity) {

        loginUsuarioEntity.setIdUsuario(loginUsuario.getIdUsuario());
        loginUsuarioEntity.setDsEmail(loginUsuario.getDsEmail());
        loginUsuarioEntity.setDsSenha(loginUsuario.getDsSenha());

        return loginUsuarioEntity;
    }
    //MÉTODO: conversão de Entity para DTO
    public LoginUsuario conversaoLoginUsuarioDTO(LoginUsuarioEntity loginUsuarioEntity, LoginUsuario loginUsuario) {

        loginUsuario.setIdUsuario(loginUsuarioEntity.getIdUsuario());
        loginUsuario.setDsEmail(loginUsuarioEntity.getDsEmail());
        loginUsuario.setDsSenha(loginUsuarioEntity.getDsSenha());

        return loginUsuario;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public LoginUsuarioEntity getAcesso(BigInteger idUsuario) {
        System.out.println("IdAcesso: " + idUsuario);
        Optional<LoginUsuarioEntity> optional = repository.findById(idUsuario);
        return optional.get();

    }
    public List<LoginUsuarioEntity> getAcessos() {
        return repository.findAll();

    }
    public List<LoginUsuarioEntity> getAcessosByEmail(String email) {
        return repository.findByDsEmail(email);
    }

    @Transactional
    public String cadastrarAcesso(LoginUsuario login, BigInteger idUsuario){

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        loginUsuarioEntity = conversaoLoginUsuarioEntity(login, loginUsuarioEntity);

        repository.save(loginUsuarioEntity);

        return "Contrato cadastrado com sucesso";
    }

    @Transactional
    public String alterarAcesso(LoginUsuario login, BigInteger idUsuario){

        LoginUsuarioEntity loginUsuarioEntity = repository.findOneByIdUsuario(idUsuario);

        loginUsuarioEntity.setDsEmail(login.getDsEmail());
        loginUsuarioEntity.setDsSenha(login.getDsSenha());

        UsuarioEntity usuario= usuarioRepository.findById(idUsuario).get();
        loginUsuarioEntity.setUsuario(usuario);

        repository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //Confirmar se haverá ou não exclusão de acessos ou apenas bloqueios
    public String excluirAcesso(BigInteger idUsuario){
        repository.deleteById(idUsuario);
        return "Exclusão de login realizada com sucesso";

    }

}