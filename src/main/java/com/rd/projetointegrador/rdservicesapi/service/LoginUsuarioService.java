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
    @Autowired
    private LoginUsuarioRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public LoginUsuarioEntity getAcesso(BigInteger idAcesso) {
        System.out.println("IdAcesso: " + idAcesso);
        Optional<LoginUsuarioEntity> optional = repository.findById(idAcesso);
        return optional.get();

    }
    public List<LoginUsuarioEntity> getAcessos(BigInteger idAcesso) {
        return repository.findAll();

    }

    //CORRIGIR POIS ESTÁ GERANDO NOVOS CÓDIGOS DE ID USUARIO
    @Transactional
    public String cadastrarAcesso(LoginUsuario login){

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();

        loginUsuarioEntity.setDsEmail(login.getDsEmail());
        loginUsuarioEntity.setDsSenha(login.getDsSenha());

        UsuarioEntity usuario= usuarioRepository.findById(BigInteger.valueOf(1)).get();
        loginUsuarioEntity.setUsuario(usuario);

        repository.save(loginUsuarioEntity);

        System.out.println(login.getIdAcesso() + " . " + login.getDsEmail() + " . " +login.getUsuario());

        return "Contrato cadastrado com sucesso";

    }

    @Transactional
    public String alterarAcesso(LoginUsuario login, BigInteger idAcesso){

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();

        loginUsuarioEntity.setDsEmail(login.getDsEmail());
        loginUsuarioEntity.setDsSenha(login.getDsSenha());

        UsuarioEntity usuario= usuarioRepository.findById(BigInteger.valueOf(1)).get();
        loginUsuarioEntity.setUsuario(usuario);

        loginUsuarioEntity= repository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //Confirmar se haverá ou não exclusão de acessos ou apenas bloqueios
    public String excluirAcesso(BigInteger idAcesso){
        repository.deleteById(idAcesso);
        return "Exclusão de login realizada com sucesso";

    }

}

