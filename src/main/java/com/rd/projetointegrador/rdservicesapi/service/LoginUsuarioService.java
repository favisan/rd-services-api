package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.LoginUsuario;
import com.rd.projetointegrador.rdservicesapi.dto.ResultData;
import com.rd.projetointegrador.rdservicesapi.entity.LoginUsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.dto.OutputMedico;
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.LoginUsuarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        //TODO: decodificar???
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
        return loginUsuarioRepository.findOneByDsEmail(email);
    }

    //MÉTODO RETORNANDO A DTO
    public LoginUsuario getAcessoByEmailDTO(String email) {
        LoginUsuarioEntity loginUsuarioEntity = getAcessoByEmail(email);
        LoginUsuario loginUsuario = new LoginUsuario();

        loginUsuario = conversaoLoginUsuarioDTO(loginUsuarioEntity, loginUsuario);

        return loginUsuario;
    }

    //PÁGINA DE LOGIN CLIENTE ------------------------------------------------------------------------
    //VALIDAR LOGIN E SENHA DE ACESSO (CLIENTE)
    @Transactional
    public ResponseEntity validarAcessoCliente(LoginUsuario loginUsuario) throws NoSuchAlgorithmException {

        String emailTela = loginUsuario.getDsEmail();
        String senhaTela = codificar(loginUsuario.getDsSenha());

        try {
            LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByDsEmail(emailTela);

            String login = loginUsuarioEntity.getDsEmail();
            String senha = loginUsuarioEntity.getDsSenha();

            if (emailTela.equals(login) && senhaTela.equals(senha)) {

                loginUsuario.setIdUsuario(loginUsuarioEntity.getIdUsuario());

                ResultData resultData = new ResultData(HttpStatus.OK.value(), "Acesso Permitido", loginUsuario);
                return ResponseEntity.status(HttpStatus.OK).body(resultData);
            } else {
                ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Usuário ou Senha incorretos!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
            }

        } catch(Exception e) {
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Acesso inexistente.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    //MÉTODO ESQUECI A SENHA
    public ResponseEntity esqueceuASenha(String email) {

        try {
            LoginUsuarioEntity loginExistente = getAcessoByEmail(email);

            //TOOO: envio de email para o endereço escolhido?
            ResultData resultData = new ResultData(HttpStatus.OK.value(), "Senha de acesso enviada para o email de cadastro");
            return ResponseEntity.status(HttpStatus.OK).body(resultData);

        } catch(Exception e) {
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Não há usuário cadastrado para este e-mail.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    //MÉTODO ESQUECI A SENHA
    public ResponseEntity conferirSenha(String senha, BigInteger idUsuario){
        try {
        senha = codificar(senha);
        LoginUsuarioEntity loginExistente = getAcesso(idUsuario);

        if(senha.equals(loginExistente.getDsSenha())) {
            ResultData resultData = new ResultData(HttpStatus.OK.value(), "Senha correta");
            return ResponseEntity.status(HttpStatus.OK).body(resultData);
        } else {
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "A senha não confere");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

        } catch(Exception e) {
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Impossível conferir a senha");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    //------------------------------------------------------------------------------------------------

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


    //GRUPO4

    //VALIDAR LOGIN E SENHA DE ACESSO TELA lOGIN
    @Transactional
    public String validarAcesso(LoginUsuario loginUsuario) throws NoSuchAlgorithmException {

        String emailTela = loginUsuario.getDsEmail();
        String senhaTela = codificar(loginUsuario.getDsSenha());

        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByDsEmail(emailTela);

        String login = loginUsuarioEntity.getDsEmail();
        String senha = loginUsuarioEntity.getDsSenha();

        if (emailTela.equals(login) && senhaTela.equals(senha)) {
            return " acesso permitido";
        } else {
            return "acesso negado";
        }
    }

    //ALTERAR SENHA SE ACESSO TELA PERFIL DO MEDICO
    @Transactional
    public String alterarDadosLogin(LoginUsuario login, BigInteger idUsuario) throws NoSuchAlgorithmException {

        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByIdUsuario(idUsuario);

        loginUsuarioEntity.setDsSenha(codificar(login.getDsSenha()));

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).get();
        loginUsuarioEntity.setUsuario(usuario);

        loginUsuarioRepository.save(loginUsuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //VALIDAR DADOS ESQUECEU A SENHA
    //TODO
    @Transactional
    public String acessoSemSenha(OutputMedico medico) {

        String  nome = medico.getNome();
        String  cpf = medico.getNrCpf();
        String  crm = medico.getNrCrm();

        //TODO: Consultar por CPF recebe uma lista de usuários
//        UsuarioEntity medicoEnt = usuarioService.consultarPorCpf(cpf);
//        String nomeBanco = medicoEnt.getNmNome();
//        String cpfBanco = medicoEnt.getNrCpf();
//        String crmBanco = medicoEnt.getNrCrm();

//        if (nome.equals(nomeBanco) && cpf.equals(cpfBanco) && crm.equals(crmBanco)) {
//            return " senha de acesso enviada para o email de cadastro";
//        } else {
//            return "acesso negado";
//        }

        return "";
    }


    //---OBSOLETO---
    @Transactional
    public String cadastrarAcesso(LoginUsuario login){

        LoginUsuarioEntity loginUsuarioEntityEx = loginUsuarioRepository.findById(login.getIdUsuario()).get();

        if(loginUsuarioEntityEx == null) {
            LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
            loginUsuarioEntity = conversaoLoginUsuarioEntity(login, loginUsuarioEntity);

            loginUsuarioEntity = loginUsuarioRepository.save(loginUsuarioEntity);

            return "Login cadastrado com sucesso. Id: " + loginUsuarioEntity.getIdUsuario();
        }

        return "Login já existe";

    }


}