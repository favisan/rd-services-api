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
import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;


@Service
public class UsuarioService {
    //GRUPO1

    //repositories
    @Autowired private UsuarioRepository repository;
    @Autowired private GeneroRepository generoRepository;
    @Autowired private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired private UfRepository ufRepository;
    @Autowired private EspecialidadeRepository especialidadeRepository;
    @Autowired private TipoContatoRepository tipoContatoRepository;
    @Autowired private LoginUsuarioRepository loginUsuarioRepository;

    //services
    //@Autowired private UfService ufService;
    //@Autowired private EnderecoService enderecoService;
    @Autowired private CidadeService cidadeService;
    @Autowired private LoginUsuarioService loginUsuarioService;


    //MÉTODO: conversão de DTO para Entity
    public UsuarioEntity conversaoUsuarioEntity(Usuario usuario, UsuarioEntity usuarioEntity) {

        GeneroEntity genero = generoRepository.findById(usuario.getIdGenero()).get();
        usuarioEntity.setGenero(genero);

        //TODO: objeto Esp Medica
        //usuarioEntity.setEspMed();

        //TODO: objeto Uf
        //usuarioEntity.setIdUfCrm(usuario.getIdUfCrm());

        //TODO: objeto preço
        //usuarioEntity.setIdPreco(usuario.getIdPreco());

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
        usuario.setIdEspMedica(usuarioEntity.getEspMed().getIdEspMed());
        usuario.setIdUfCrm(usuarioEntity.getUf().getIdUf());
        usuario.setIdTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
        usuario.setNmNome(usuarioEntity.getNmNome());
        usuario.setDtNascimento(usuarioEntity.getDtNascimento());
        usuario.setNrCpf(usuarioEntity.getNrCpf());
        usuario.setNrCrm(usuarioEntity.getNrCrm());
        usuario.setDsEndImg(usuarioEntity.getDsEndImg());
        usuario.setIdPreco(usuarioEntity.getPreco().getIdPreco());

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


    //GRUPO4 --------------------------------------------------------------------------

    //BUSCAR MEDICO POR ID
    public OutputMedico getMedico(BigInteger id) {
        System.out.println("ID: " + id);
        UsuarioEntity entity = repository.findById(id).get();
        OutputMedico user = new OutputMedico();
        user.setIdUsuario(entity.getIdUsuario());
        user.setNome(entity.getNmNome());
        user.setNrCrm(entity.getNrCrm());

        UfEntity ufEntity = entity.getUf();
        Uf uf = new Uf();
        uf.setDsUf(ufEntity.getDsUf());
        user.setUfCrm(uf);

        EspMedEntity espMedEntity = entity.getEspMed();
        EspMed espMed = new EspMed();
        espMed.setDsEspMed(espMedEntity.getDsEspMed());
        user.setIdEspMed(espMed);

        user.setNome(entity.getNmNome());
        user.setDtNascimento(entity.getDtNascimento());
        user.setNrCpf(entity.getNrCpf());
        user.setNrCrm(entity.getNrCrm());

        PrecoEntity precoEntity = entity.getPreco();
        Preco preco = new Preco();
        preco.setVlConsulta(precoEntity.getVlConsulta());
        user.setPreco(preco);

        List<ContatoEntity> contatosEntity = entity.getContatos();
        List<Contato> contatos = new ArrayList<>();
        for (ContatoEntity contatoEntity : contatosEntity) {
            Contato contato = new Contato();
            contato.setDsContato(contatoEntity.getDsContato());

            contatos.add(contato);
        }
        user.setContatos(contatos);

        List<EnderecoEntity> enderecoEntities = entity.getEnderecos();
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoEntity enderecoEntity : enderecoEntities) {
            Endereco endereco = new Endereco();
            endereco.setDsEndereco(enderecoEntity.getDsEndereco());
            endereco.setDsBairro(enderecoEntity.getDsBairro());
            endereco.setIdCidade(enderecoEntity.getIdCidade());
            endereco.setDsComplemento(enderecoEntity.getDsComplemento());
            endereco.setNrCep(enderecoEntity.getNrCep());

            enderecos.add(endereco);
        }
        user.setEnderecos(enderecos);

        return user;
    }

    //LISTAR TODOS OS MEDICOS
    public List<UsuarioEntity> getMedicos() {
        return repository.findAll();
    }


    //ALTERAR CADASTRO DE PERFIL DO MEDICO
    @Transactional
    public String alterarMedico(InputMedico inputMedico, BigInteger id) {

        UsuarioEntity entity = repository.findById(id).get();

        EspMedEntity espEntity = especialidadeRepository.findById(inputMedico.getIdEspMed().getIdEspMed()).get();
        entity.setEspMed(espEntity);

        UfEntity ufEntity = ufRepository.findById(inputMedico.getUf().getIdUf()).get();
        entity.setUf(ufEntity);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(2)).get();
        entity.setTipoUsuario(tipoUsuarioEntity);

        entity.setNmNome(inputMedico.getNome());
        entity.setDtNascimento(inputMedico.getDtNascimento());
        entity.setNrCpf(inputMedico.getNrCpf());
        entity.setNrCrm(inputMedico.getNrCrm());

        PrecoEntity precoEntity = new PrecoEntity();
        Preco preco = inputMedico.getPreco();
        precoEntity.setVlConsulta(preco.getVlConsulta());
        entity.setPreco(precoEntity);

        List<EnderecoEntity> enderecosEntity = entity.getEnderecos();
        for (Endereco endereco : inputMedico.getEnderecos()) {
            EnderecoEntity enderecoEntity = new EnderecoEntity();
            enderecoEntity.setIdCidade(endereco.getIdCidade());
            enderecoEntity.setDsComplemento(endereco.getDsComplemento());
            enderecoEntity.setDsEndereco(endereco.getDsEndereco());
            enderecoEntity.setDsBairro(endereco.getDsBairro());
            enderecoEntity.setNrCep(endereco.getNrCep());
        }
        entity.setEnderecos(enderecosEntity);

        List<ContatoEntity> contatosEntity = entity.getContatos();
        for (Contato contato : inputMedico.getContatos()) {
            ContatoEntity contatoEntity = new ContatoEntity();
            TipoContatoEntity tpContatoEntity = tipoContatoRepository.findById(BigInteger.valueOf(2)).get();
            contatoEntity.setTipoContato(tpContatoEntity);
            contatoEntity.setDsContato(contato.getDsContato());
        }
        entity.setContatos(contatosEntity);

        repository.save(entity);

        return "Alteração realizado com sucesso";
    }

    //CADASTRAR MEDICO
    @Transactional
    public String cadastrarMedico(InputMedico inputMedico) throws NoSuchAlgorithmException {

        UsuarioEntity entity = new UsuarioEntity();

        EspMedEntity espEntity = especialidadeRepository.findById(inputMedico.getIdEspMed().getIdEspMed()).get();
        entity.setEspMed(espEntity);

        UfEntity ufEntity = ufRepository.findById(inputMedico.getUf().getIdUf()).get();
        entity.setUf(ufEntity);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(2)).get();
        entity.setTipoUsuario(tipoUsuarioEntity);

        entity.setNmNome(inputMedico.getNome());
        entity.setDtNascimento(inputMedico.getDtNascimento());
        entity.setNrCpf(inputMedico.getNrCpf());
        entity.setNrCrm(inputMedico.getNrCrm());

        PrecoEntity precoEntity = new PrecoEntity();
        Preco preco = inputMedico.getPreco();
        precoEntity.setVlConsulta(preco.getVlConsulta());
        entity.setPreco(precoEntity);

        List<EnderecoEntity> enderecosEntity = new ArrayList<>();
        for (Endereco endereco : inputMedico.getEnderecos()) {
            EnderecoEntity enderecoEntity = new EnderecoEntity();
            enderecoEntity.setIdCidade(endereco.getIdCidade());
            enderecoEntity.setDsComplemento(endereco.getDsComplemento());
            enderecoEntity.setDsEndereco(endereco.getDsEndereco());
            enderecoEntity.setDsBairro(endereco.getDsBairro());
            enderecoEntity.setNrCep(endereco.getNrCep());

            enderecosEntity.add(enderecoEntity);
        }

        entity.setEnderecos(enderecosEntity);

        List<ContatoEntity> contatosEntity = new ArrayList<>();
        for (Contato contato : inputMedico.getContatos()) {
            ContatoEntity contatoEntity = new ContatoEntity();
            TipoContatoEntity tpContatoEntity = tipoContatoRepository.findById(BigInteger.valueOf(2)).get();
            contatoEntity.setTipoContato(tpContatoEntity);
            contatoEntity.setDsContato(contato.getDsContato());

            contatosEntity.add(contatoEntity);
        }

        entity.setContatos(contatosEntity);
        repository.save(entity);

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        LoginUsuario loginUsuario = inputMedico.getLogin();
        
        loginUsuarioEntity.setIdUsuario(entity.getIdUsuario());
        loginUsuarioEntity.setDsEmail(loginUsuario.getDsEmail());
        loginUsuarioEntity.setDsSenha(loginUsuarioService.codificar(loginUsuario.getDsSenha()));

        loginUsuarioRepository.save(loginUsuarioEntity);

        return "Usuário cadastrado com sucesso";
    }

    //EXIBIR TELA DE PERFIL DO MEDICO
    public PerfilMedico mostrarTelaPerfil(BigInteger idMedico) {
        PerfilMedico perfilMedico = new PerfilMedico();
        perfilMedico.setMedico(getMedico(idMedico));
        perfilMedico.setDsEmail(loginUsuarioRepository.findOneByIdUsuario(idMedico).getDsEmail());
        perfilMedico.setCidades(cidadeService.buscarCidadePorUf(getMedico(idMedico).getUfCrm().getIdUf()));
        perfilMedico.setEspecialidades(especialidadeRepository.findAll());
        perfilMedico.setUfs(ufRepository.findAll());

        return perfilMedico;
    }

    //EXIBIR LISTAS DA TELA DE CADASTRO DO MEDICO
    public CadastroMedico mostrarTelaCadastro(BigInteger idUf) {
        CadastroMedico cadastroMedico = new CadastroMedico();
        cadastroMedico.setCidades(cidadeService.buscarCidadePorUf(idUf));
        cadastroMedico.setEspecialidades(especialidadeRepository.findAll());
        cadastroMedico.setUfs(ufRepository.findAll());

        return cadastroMedico;
    }
}
