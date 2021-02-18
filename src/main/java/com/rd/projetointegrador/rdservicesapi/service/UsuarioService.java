package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rd.projetointegrador.rdservicesapi.entity.UsuarioEntity;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;

import java.util.Optional;
import java.security.NoSuchAlgorithmException;


@Service
public class UsuarioService {
    //GRUPO1

    //repositories

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private UfRepository ufRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    @Autowired
    private TipoContatoRepository tipoContatoRepository;
    @Autowired
    private LoginUsuarioRepository loginUsuarioRepository;
    @Autowired
    private PrecoRepository precoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    //services
    //@Autowired private UfService ufService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private LoginUsuarioService loginUsuarioService;

    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    //MÉTODO: conversão de DTO para Entity
    public UsuarioEntity conversaoUsuarioEntity(Usuario usuario, UsuarioEntity usuarioEntity) {

        try {
            GeneroEntity genero = generoRepository.findById(usuario.getIdGenero()).get();
            usuarioEntity.setGenero(genero);

            if (usuario.getIdEspMedica() != null) {
                EspMedEntity espMedEntity = especialidadeRepository.findById(usuario.getIdEspMedica()).get();
                usuarioEntity.setEspMed(espMedEntity);
            }

            if (usuario.getIdUfCrm() != null) {
                UfEntity ufEntity = ufRepository.findById(usuario.getIdUfCrm()).get();
                usuarioEntity.setUf(ufEntity);
            }

            if (usuario.getPreco() != null) {
                PrecoEntity precoEntity = precoRepository.findById(usuario.getPreco().getIdPreco()).get();
                usuarioEntity.setPreco(precoEntity);
            }

            List<EnderecoEntity> enderecosEntities = new ArrayList();
            enderecosEntities = enderecoService.conversaoEnderecosEntities(usuario.getEnderecos(), enderecosEntities);
            usuarioEntity.setEnderecos(enderecosEntities);

            TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(usuario.getIdTipoUsuario()).get();
            usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

            usuarioEntity.setNmNome(usuario.getNmNome());
            Date dataNascimento = SDF.parse(usuario.getDtNascimento());
            usuarioEntity.setDtNascimento(dataNascimento);
            usuarioEntity.setNrCpf(usuario.getNrCpf());
            usuarioEntity.setNrCrm(usuario.getNrCrm());
            usuarioEntity.setDsEndImg(usuario.getDsEndImg());

            return usuarioEntity;

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    //MÉTODO: conversão de Entity para DTO
    public Usuario conversaoUsuarioDTO(UsuarioEntity usuarioEntity, Usuario usuario) {

        List<Endereco> enderecos = new ArrayList();
        enderecos = enderecoService.conversaoEnderecosDTO(usuarioEntity.getEnderecos(), enderecos);
        usuario.setEnderecos(enderecos);

        usuario.setIdUsuario(usuarioEntity.getIdUsuario());

        GeneroEntity generoEntity = usuarioEntity.getGenero();
        if (generoEntity != null) {
            usuario.setIdGenero(generoEntity.getIdGenero());
        }

        EspMedEntity espMedEntity = usuarioEntity.getEspMed();
        if (espMedEntity != null) {
            usuario.setIdEspMedica(espMedEntity.getIdEspMed());
        }

        UfEntity ufEntity = usuarioEntity.getUf();
        if (ufEntity != null) {
            usuario.setIdUfCrm(usuarioEntity.getUf().getIdUf());
        }

        PrecoEntity precoEntity = usuarioEntity.getPreco();

        if (precoEntity != null) {
            Preco preco = new Preco();
            preco.setVlConsulta(precoEntity.getVlConsulta());
            usuario.setPreco(preco);
        }

        usuario.setIdTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
        usuario.setNmNome(usuarioEntity.getNmNome());

        if(usuarioEntity.getDtNascimento() != null) {
            String dtNascimento = SDF.format(usuarioEntity.getDtNascimento());
            usuario.setDtNascimento(dtNascimento);
        }

        usuario.setNrCpf(usuarioEntity.getNrCpf());
        usuario.setNrCrm(usuarioEntity.getNrCrm());
        usuario.setDsEndImg(usuarioEntity.getDsEndImg());

        return usuario;
    }

    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<UsuarioEntity> conversaoUsuariosEntity(List<Usuario> usuarios, List<UsuarioEntity> usuariosEntity) {

        for (Usuario usuario : usuarios) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

            usuariosEntity.add(usuarioEntity);
        }

        return usuariosEntity;
    }

    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Usuario> conversaoUsuariosDTO(List<UsuarioEntity> usuariosEntities, List<Usuario> usuarios) {

        for (UsuarioEntity usuarioEntity : usuariosEntities) {
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

    public List<UsuarioEntity> consultarPorCpf(String nrCpf) {
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
    public String cadastrarUsuario(Usuario usuario) {

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        String cpf = usuarioEntity.getNrCpf();
        List<UsuarioEntity> usuarioExistente = repository.findByNrCpf(cpf);

        if (usuarioExistente.isEmpty()) {

            usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);
            repository.save(usuarioEntity);

            return "Usuário cadastrado com sucesso";
        }

        return "Erro. Cpf já utilizado.";
    }

    @Transactional
    public String alterarUsuario(Usuario usuario, BigInteger idUsuario) {

        UsuarioEntity usuarioEntity = getUsuario(idUsuario);
        usuarioEntity = conversaoUsuarioEntity(usuario, usuarioEntity);

        usuarioEntity = repository.save(usuarioEntity);
        return "Alteração realizada com sucesso";
    }

    //EXCLUSÃO OU BLOQUEIO????
    public String excluirUsuario(BigInteger idUsuario) {
        repository.deleteById(idUsuario);
        return "Exclusão de usuário realizada com sucesso";

    }


    //GRUPO4 --------------------------------------------------------------------------

    //BUSCAR MEDICO POR ID OK

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
        user.setEspMed(espMed);
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
            contato.setNrDdd(contatoEntity.getNrDdd());
            contatos.add(contato);
        }
        user.setContatos(contatos);
        List<EnderecoEntity> enderecoEntities = entity.getEnderecos();
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoEntity enderecoEntity : enderecoEntities) {
            Endereco endereco = new Endereco();
            endereco.setDsEndereco(enderecoEntity.getDsEndereco());
            endereco.setDsBairro(enderecoEntity.getDsBairro());
            CidadeEntity cidadeEntity = enderecoEntity.getCidade();
            Cidade cidade = new Cidade();
            cidade.setIdCidade(cidadeEntity.getIdCidade());
            cidade.setDsCidade(cidadeEntity.getDsCidade());
            UfEntity ufEntity1 = enderecoEntity.getCidade().getUf();
            Uf uf1 = new Uf();
            uf1.setIdUf(ufEntity1.getIdUf());
            uf1.setDsUf(ufEntity1.getDsUf());
            cidade.setUf(uf);
            endereco.setCidade(cidade);
            endereco.setDsComplemento(enderecoEntity.getDsComplemento());
            endereco.setNrCep(enderecoEntity.getNrCep());
            enderecos.add(endereco);
        }
        user.setEnderecos(enderecos);
        return user;
    }

    //LISTAR TODOS OS MEDICOS OK
    public List<UsuarioEntity> getMedicos() {
        return repository.findAll();
    }

    //ALTERAR CADASTRO DE PERFIL DO MEDICO OK
    @Transactional
    public boolean alterarMedico(InputMedico inputMedico, BigInteger id) {
        UsuarioEntity entity = repository.findById(id).get();
        EspMedEntity espEntity = especialidadeRepository.findById(inputMedico.getEspMed().getIdEspMed()).get();
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
            CidadeEntity cidadeEntity = enderecoEntity.getCidade();
            Cidade cidade = new Cidade();
            cidade.setIdCidade(cidadeEntity.getIdCidade());
            UfEntity ufEntity1 = enderecoEntity.getCidade().getUf();
            Uf uf1 = new Uf();
            uf1.setIdUf(ufEntity1.getIdUf());
            cidade.setUf(uf1);
            endereco.setCidade(cidade);
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
        return true;
    }

    //CADASTRAR MEDICO OK
    @Transactional
    public Boolean cadastrarMedico(InputMedico inputMedico) throws NoSuchAlgorithmException {

        UsuarioEntity entity = new UsuarioEntity();

        EspMedEntity espEntity = especialidadeRepository.findById(inputMedico.getEspMed().getIdEspMed()).get();
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
            CidadeEntity cidadeEntity = new CidadeEntity();
            Cidade cidade = endereco.getCidade();
            cidadeEntity.setIdCidade(cidade.getIdCidade());
            UfEntity ufEntity1 = new UfEntity();
            Uf uf = endereco.getCidade().getUf();
            ufEntity1.setIdUf(uf.getIdUf());
            cidadeEntity.setUf(ufEntity1);
            enderecoEntity.setCidade(cidadeEntity);
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

        return true;

    }

    //EXIBIR TELA DE PERFIL DO MEDICO OK
    public PerfilMedico mostrarTelaPerfil(BigInteger idMedico) {
        PerfilMedico perfilMedico = new PerfilMedico();
        perfilMedico.setMedico(getMedico(idMedico));
        perfilMedico.setDsEmail(loginUsuarioRepository.findOneByIdUsuario(idMedico).getDsEmail());
        perfilMedico.setCidades(cidadeService.listarCidade());
        perfilMedico.setEspecialidades(especialidadeRepository.findAll());
        perfilMedico.setUfs(ufRepository.findAll());

        return perfilMedico;
    }

    //EXIBIR LISTAS DA TELA DE CADASTRO DO MEDICO OK
    public CadastroMedico mostrarTelaCadastro() {
        CadastroMedico cadastroMedico = new CadastroMedico();
        cadastroMedico.setCidades(cidadeRepository.findAll());
        cadastroMedico.setEspecialidades(especialidadeRepository.findAll());
        cadastroMedico.setUfs(ufRepository.findAll());

        return cadastroMedico;
    }

    //GRUPO3 --------------------------------------------------------------------------

    public BigInteger getUsuarioPorCPF(String cpf) {

        UsuarioEntity user = repository.findOneByNrCpf(cpf);
        return user.getIdUsuario();
    }

    //BUSCAR CPF PARA IMPEDIR CADATRO COM MESMO CPF OK
    public UsuarioEntity consultarPorCpfMedico(String nrCpf) {
        return repository.findOneByNrCpf(nrCpf);
    }

}
