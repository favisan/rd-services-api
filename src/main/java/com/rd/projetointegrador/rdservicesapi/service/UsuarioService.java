package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    //repositories
    @Autowired private UsuarioRepository repository;
    @Autowired private GeneroRepository generoRepository;
    @Autowired private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired private LoginUsuarioRepository loginUsuarioRepository;
    @Autowired private ContratoRepository contratoRepository;
    @Autowired private CartaoRepository cartaoRepository;

    //services
    //@Autowired private UfService ufService;
    @Autowired private GeneroService generoService;
    @Autowired private PlanosService planosService;
    @Autowired private ContratoService contratoService;
    @Autowired private LoginUsuarioService luService;
    @Autowired private CartaoService cartaoService;
    //@Autowired private EnderecoService enderecoService;


    //MÉTODO: conversão de DTO para Entity
    public UsuarioEntity conversaoUsuarioEntity(Usuario usuario, UsuarioEntity usuarioEntity) {

        GeneroEntity genero = generoRepository.findById(usuario.getIdGenero()).get();
        usuarioEntity.setGenero(genero);

        //TODO: objeto Esp Medica
        usuarioEntity.setIdEspMedica(usuario.getIdEspMedica());

        //TODO: objeto Uf
        usuarioEntity.setIdUfCrm(usuario.getIdUfCrm());

        //TODO: conversao endereços
        //List<EnderecoEntity> enderecosEntities = new ArrayList();
        //enderecosEntities = conversaoEnderecosEntities(usuario.getEnderecos(), enderecosEntities)
        //usuarioEntity.setEnderecos(enderecosEntities);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(usuario.getIdTipoUsuario()).get();
        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

        usuarioEntity.setNmNome(usuario.getNmNome());
        usuarioEntity.setDtNascimento(usuario.getDtNascimento());
        usuarioEntity.setNrCpf(usuario.getNrCpf());
        usuarioEntity.setNrCrm(usuario.getNrCrm());
        usuarioEntity.setDsEndImg(usuario.getDsEndImg());
        usuarioEntity.setIdPreco(usuario.getIdPreco());

        return usuarioEntity;
    }
    //MÉTODO: conversão de Entity para DTO
    public Usuario conversaoUsuarioDTO(UsuarioEntity usuarioEntity, Usuario usuario) {

        //TODO: conversao endereços
        //List<Endereco> enderecos = new ArrayList();
        //enderecos = conversaoEnderecosDTO(usuarioEntity.getEnderecos(), enderecos)
        //usuario.setEnderecos(enderecos);

        usuario.setIdUsuario(usuarioEntity.getIdUsuario());
        usuario.setIdGenero(usuarioEntity.getGenero().getIdGenero());
        usuario.setIdEspMedica(usuarioEntity.getIdEspMedica());
        usuario.setIdUfCrm(usuarioEntity.getIdUfCrm());
        usuario.setIdTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
        usuario.setNmNome(usuarioEntity.getNmNome());
        usuario.setDtNascimento(usuarioEntity.getDtNascimento());
        usuario.setNrCpf(usuarioEntity.getNrCpf());
        usuario.setNrCrm(usuarioEntity.getNrCrm());
        usuario.setDsEndImg(usuarioEntity.getDsEndImg());
        usuario.setIdPreco(usuarioEntity.getIdPreco());

        return usuario;
    }
    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<UsuarioEntity> conversaoUsuariosEntity(List<Usuario> usuarios, List<UsuarioEntity> usuariosEntity) {

        for(Usuario usuario: usuarios) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

            usuariosEntity.add(usuarioEntity);
        }

        return usuariosEntity;
    }
    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Usuario> conversaoUsuariosDTO(List<UsuarioEntity> usuariosEntities, List<Usuario> usuarios) {

        for(UsuarioEntity usuarioEntity: usuariosEntities) {
            Usuario usuario = new Usuario();
            usuario = conversaoUsuarioDTO(usuarioEntity, usuario);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public UsuarioEntity getUsuario(BigInteger idUsuario) {
        Optional<UsuarioEntity> optional = repository.findById(idUsuario);
        return optional.get();
    }
    public List<UsuarioEntity> getUsuarios() {
        return repository.findAll();

    }
    public List<UsuarioEntity> consultarPorNome(String nmUsuario) {
        return repository.findByNmNome(nmUsuario);
    }
    public List<UsuarioEntity> consultarPorCpf(String nrCpf){
        return repository.findByNrCpf(nrCpf);
    }

    //MÉTODOS RETORNANDO A DTO
    public Usuario getUsuarioDTO(BigInteger idUsuario) {
            UsuarioEntity usuarioEntity = getUsuario(idUsuario);
            Usuario usuario = new Usuario();

            usuario = conversaoUsuarioDTO(usuarioEntity, usuario);

            return usuario;
    }
    public List<Usuario> getUsuariosDTO() {
        List<UsuarioEntity> usuarioEntities = getUsuarios();
        List<Usuario> usuarios = new ArrayList<>();

        usuarios = conversaoUsuariosDTO(usuarioEntities, usuarios);

        return usuarios;
    }

    //TODO: CADASTRO DE USUÁRIO - TELA DE CADASTRO
    //TODO: faltando contato e endereço usuário
    @Transactional
    public String cadastrarUsuario(InputUsuario inputUsuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        ContratoEntity contratoEntity = new ContratoEntity();
        CartaoEntity cartaoEntity= new CartaoEntity();

        //VALIDAR CPF
        String cpf = usuarioEntity.getNrCpf();
        List<UsuarioEntity> usuarioExistente = repository.findByNrCpf(cpf);

        //VALIDAR E-MAIL
        String email = loginUsuarioEntity.getDsEmail();
        LoginUsuarioEntity loginExistente = loginUsuarioRepository.findByDsEmail(email);


        if(usuarioExistente.isEmpty() && loginExistente == null) {

            //Passando dados do Usuário
            usuarioEntity = conversaoUsuarioEntity(inputUsuario.getUsuario(), usuarioEntity);
            usuarioEntity = repository.save(usuarioEntity);
            BigInteger novoId = usuarioEntity.getIdUsuario();

            //Entidade LoginUsuario
            inputUsuario.getLoginUsuario().setIdUsuario(novoId);
            loginUsuarioEntity = luService.conversaoLoginUsuarioEntity(inputUsuario.getLoginUsuario(), loginUsuarioEntity);
            loginUsuarioRepository.save(loginUsuarioEntity);

            //Entidade Contrato
            inputUsuario.getContrato().setIdUsuario(novoId);
            contratoEntity = contratoService.conversaoContratoEntity(inputUsuario.getContrato(), contratoEntity);
            contratoRepository.save(contratoEntity);

            //Entidade Cartao
            inputUsuario.getCartao().setIdUsuario(novoId);
            cartaoEntity = cartaoService.conversaoCartaoEntity(inputUsuario.getCartao(), cartaoEntity);
            cartaoRepository.save(cartaoEntity);

            //Entidade Contato
            //TODO: fazer relacao com contatoService para cadastrar telefone

        /*
        //contato
        private String ddd;
        private String celular;*/

            return "Usuário cadastrado com sucesso";
        }

        return "Erro. Usuário já cadastrado.";
    }

    @Transactional
    public String cadastrarUsuario(Usuario usuario){

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        String cpf = usuarioEntity.getNrCpf();
        List<UsuarioEntity> usuarioExistente = repository.findByNrCpf(cpf);

        if(usuarioExistente.isEmpty()) {

            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);
            repository.save(usuarioEntity);

            return "Usuário cadastrado com sucesso";
        }

        return "Erro. Cpf já utilizado.";
    }

    @Transactional
    public String alterarUsuario(Usuario usuario, BigInteger idUsuario){

        UsuarioEntity usuarioEntity = getUsuario(idUsuario);
        usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

        usuarioEntity = repository.save(usuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //EXCLUSÃO OU BLOQUEIO????
    public String excluirUsuario(BigInteger idUsuario){
        repository.deleteById(idUsuario);
        return "Exclusão de usuário realizada com sucesso";

    }

    //TELA DE CADASTRO - GET
    //TODO: faltando recuperar lista de Ufs para o Form
    public FormularioCadastro getFormularioCadastro() {
        FormularioCadastro formularioCadastro = new FormularioCadastro();

        //formularioCadastro.setUfs(ufService.getUfsDTO());
        formularioCadastro.setGenero(generoService.getGenerosDTO());
        formularioCadastro.setPlanos(planosService.getPlanosDTO());
        return formularioCadastro;
    }

    //TELA MEUS DADOS - GET
    //TODO: faltando dados relativos a contato, uf
    public FormularioMeusDados getFormularioMeusDados(BigInteger id) {
        FormularioCadastro formularioCadastro = getFormularioCadastro();
        FormularioMeusDados formularioMeusDados = new FormularioMeusDados();

        Boolean teste = repository.existsById(id);
        if(teste) {
            //buscar entities
            UsuarioEntity usuarioEntity = getUsuario(id);
            Usuario usuario = new Usuario();
            usuario = conversaoUsuarioDTO(usuarioEntity, usuario);

            //buscar e-mail de login
            String email = loginUsuarioRepository.findOneByIdUsuario(id).getDsEmail();

            //buscar idPlano no contrato
            List<ContratoEntity> contratosEntities = contratoRepository.findByUsuarioOrderByDtVigencia(usuarioEntity);
            BigInteger idPlanoVigente = contratosEntities.get(0).getPlanosEntity().getIdPlano();

            //buscar lista de contatos
            //Optional<ContatoEntity> optional = repository.findByIdUsuario(id);

            formularioMeusDados.setUsuario(usuario);
            formularioMeusDados.setDsEmail(email);
            //formularioMeusDados.setContato();
            formularioMeusDados.setIdPlano(idPlanoVigente);

        }

        //formularioMeusDados.setUfs(formularioCadastro.getUfs());
        formularioMeusDados.setGenero(formularioCadastro.getGenero());
        formularioMeusDados.setPlanos(formularioCadastro.getPlanos());

        return formularioMeusDados;
    }

}