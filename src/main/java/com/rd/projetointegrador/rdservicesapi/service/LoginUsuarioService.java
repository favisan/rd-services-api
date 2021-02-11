package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.LoginUsuario;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.dto.OutputMedico;
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
    //GRUPO1

    @Autowired private LoginUsuarioRepository loginUsuarioRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private UsuarioService usuarioService;


    //MÉTODO: conversão de DTO para Entity
    public LoginUsuarioEntity conversaoLoginUsuarioEntity(LoginUsuario loginUsuario, LoginUsuarioEntity loginUsuarioEntity){
        try {

            loginUsuarioEntity.setIdUsuario(loginUsuario.getIdUsuario());
            loginUsuarioEntity.setDsEmail(loginUsuario.getDsEmail());
            loginUsuarioEntity.setDsSenha(codificar(loginUsuario.getDsSenha()));

            return loginUsuarioEntity;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }
    //MÉTODO: conversão de Entity para DTO
    public LoginUsuario conversaoLoginUsuarioDTO(LoginUsuarioEntity loginUsuarioEntity, LoginUsuario loginUsuario) {

        try {
            loginUsuario.setIdUsuario(loginUsuarioEntity.getIdUsuario());
            loginUsuario.setDsEmail(loginUsuarioEntity.getDsEmail());

            //TODO: Preciso decoficar a senha?
            loginUsuario.setDsSenha(loginUsuarioEntity.getDsSenha());

            return loginUsuario;

        }catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }

    }

    //MÉTODOS RETORNANDO A ENTITY
    public LoginUsuarioEntity getAcesso(BigInteger idUsuario) {
        System.out.println("IdAcesso: " + idUsuario);
        Optional<LoginUsuarioEntity> optional = loginUsuarioRepository.findById(idUsuario);
        return optional.get();

    }
    public List<LoginUsuarioEntity> getAcessos() {
        return loginUsuarioRepository.findAll();

    }
    public LoginUsuarioEntity getAcessoByEmail(String email) {
        return loginUsuarioRepository.findByDsEmail(email);
    }

    //MÉTODO RETORNANDO A DTO
    public LoginUsuario getAcessoByEmailDTO(String email) {
        LoginUsuarioEntity loginUsuarioEntity = getAcessoByEmail(email);
        LoginUsuario loginUsuario = new LoginUsuario();

        loginUsuario = conversaoLoginUsuarioDTO(loginUsuarioEntity, loginUsuario);

        return loginUsuario;
    }

    //VALIDAR LOGIN E SENHA DE ACESSO TELA lOGIN
    @Transactional
    public String validarAcesso(LoginUsuario loginUsuario) throws NoSuchAlgorithmException {

        String emailTela = loginUsuario.getDsEmail();
        String senhaTela = codificar(loginUsuario.getDsSenha());

        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findByDsEmail(emailTela);
        String login = loginUsuarioEntity.getDsEmail();
        String senha = loginUsuarioEntity.getDsSenha();

        if (emailTela.equals(login) && senhaTela.equals(senha)) {
            return " acesso permitido";
        } else {
            return "acesso negado";
        }
    }

    //MÉTODO ESQUECI A SENHA
    public String esqueceuASenha(String email) {

        try {
            LoginUsuarioEntity loginExistente = getAcessoByEmail(email);
            System.out.println(loginExistente.getDsSenha() + loginExistente.getDsEmail());

            //TOOO: envio de email para o endereço escolhido?
            return " Senha de acesso enviada para o email de cadastro";

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "Não há usuário cadastrado para este e-mail.";
        }

    }

    @Transactional
    public String alterarAcesso(LoginUsuario login, BigInteger idUsuario){

        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByIdUsuario(idUsuario);

        loginUsuarioEntity.setDsEmail(login.getDsEmail());
        loginUsuarioEntity.setDsSenha(login.getDsSenha());

        UsuarioEntity usuario= usuarioRepository.findById(idUsuario).get();
        loginUsuarioEntity.setUsuario(usuario);

        loginUsuarioRepository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //Confirmar se haverá ou não exclusão de acessos ou apenas bloqueios
    public String excluirAcesso(BigInteger idUsuario){
        loginUsuarioRepository.deleteById(idUsuario);
        return "Exclusão de login realizada com sucesso";

    }
    //---OBSOLETO---
    @Transactional
    public String cadastrarAcesso(LoginUsuario login, BigInteger idUsuario){

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        loginUsuarioEntity = conversaoLoginUsuarioEntity(login, loginUsuarioEntity);

        loginUsuarioRepository.save(loginUsuarioEntity);

        return "Contrato cadastrado com sucesso";
    }


    //GRUPO4

    //CRIPTOGRAFAR SENHA USUARIO OK
    public String codificar(String senha) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
            return hash.toString(1);
        } catch (Exception e) {
            return "";
        }
    }

    //ALTERAR SENHA SE ACESSO TELA PERFIL DO MEDICO OK
    @Transactional
    public String alterarDadosLogin(LoginUsuario login, BigInteger idUsuario) throws NoSuchAlgorithmException {

        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByIdUsuario(idUsuario);

        loginUsuarioEntity.setDsSenha(codificar(login.getDsSenha()));

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).get();
        loginUsuarioEntity.setUsuario(usuario);

        loginUsuarioRepository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //VALIDAR DADOS ESQUECEU A SENHA OK
    @Transactional
    public String acessoSemSenha(OutputMedico medico) {

        String  nome = medico.getNome();
        String  cpf = medico.getNrCpf();
        String  crm = medico.getNrCrm();

        UsuarioEntity medicoEnt = usuarioService.consultarPorCpfMedico(cpf);
        String nomeBanco = medicoEnt.getNmNome();
        String cpfBanco = medicoEnt.getNrCpf();
        String crmBanco = medicoEnt.getNrCrm();

        if (nome.equals(nomeBanco) && cpf.equals(cpfBanco) && crm.equals(crmBanco)) {
            return " senha de acesso enviada para o email de cadastro";
        } else {
            return "acesso negado";
        }
    }
}

