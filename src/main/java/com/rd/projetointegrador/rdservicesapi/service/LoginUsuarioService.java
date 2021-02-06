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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class LoginUsuarioService {

    @Autowired
    private LoginUsuarioRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //CRIPTOGRAFAR SENHA USUARIO
    public String codificar(String senha) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
            return hash.toString(1);
        } catch (Exception e) {
            return "";
        }
    }

    //BUSCAR DADOS DE LOGIN POR EMAIL - USADO NO METODO VALIDAR
    public LoginUsuarioEntity getAcessoByEmail(String email) {
        return repository.findByDsEmail(email);
    }


    //ALTERAR LOGIN E SENHA SE ACESSO TELA PERFIL DO MEDICO
    @Transactional
    public String alterarDadosLogin(LoginUsuario login, BigInteger idUsuario) throws NoSuchAlgorithmException {

        LoginUsuarioEntity loginUsuarioEntity = repository.findOneByIdUsuario(idUsuario);

        loginUsuarioEntity.setDsEmail(login.getDsEmail());
        loginUsuarioEntity.setDsSenha(codificar(login.getDsSenha()));

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).get();
        loginUsuarioEntity.setUsuario(usuario);

        repository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //VALIDAR LOGIN E SENHA DE ACESSO TELA lOGIN
    @Transactional
    public String validarAcesso(LoginUsuario loginUsuario) throws NoSuchAlgorithmException {

        String emailTela = loginUsuario.getDsEmail();
        String senhaTela = codificar(loginUsuario.getDsSenha());

        LoginUsuarioEntity loginUsuarioEntity = repository.findByDsEmail(emailTela);
        String login = loginUsuarioEntity.getDsEmail();
        String senha = loginUsuarioEntity.getDsSenha();

        if (emailTela.equals(login) && senhaTela.equals(senha)) {
            return " acesso permitido";
        } else {
            return "acesso negado";
        }
    }
}