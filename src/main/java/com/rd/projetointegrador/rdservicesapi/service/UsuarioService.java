package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

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

    public Usuario getMedico(BigInteger id){
        System.out.println("ID: " + id);
        UsuarioEntity entity = repository.findById(id).get();
        Usuario user = new Usuario();
        user.setIdUsuario(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNrCrm(entity.getNrCrm());

        UfEntity ufEntity = entity.getUf();
        Uf uf = new Uf();
        uf.setDsUf(ufEntity.getDsUf());
        user.setUf(uf);

        EspMedEntity espMedEntity = entity.getEspMed();
        EspMed espMed = new EspMed();
        espMed.setDsEspMed(espMedEntity.getDsEspMed());
        user.setIdEspMed(espMed);

        user.setNome(entity.getNome());
        user.setDtNascimento(entity.getDtNascimento());
        user.setNrCpf(entity.getNrCpf());
        user.setNrCrm(entity.getNrCrm());

        PrecoEntity precoEntity = entity.getPreco();
        Preco preco = new Preco();
        preco.setVlConsulta(precoEntity.getVlConsulta());
        user.setPreco(preco);

        List<ContatoEntity> contatosEntity = entity.getContatos();
        List<Contato> contatos = new ArrayList<>();
        for(ContatoEntity contatoEntity : contatosEntity){
            Contato contato = new Contato();
            contato.setDsContato(contatoEntity.getDsContato());

            contatos.add(contato);
        }
        user.setContatos(contatos);

        List<EnderecoEntity> enderecoEntities = entity.getEnderecos();
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoEntity enderecoEntity : enderecoEntities){
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

    public List<UsuarioEntity> getUsuarios() {
        return repository.findAll();
    }

    public List<UsuarioSaida> getMedicos() {
        List<UsuarioEntity> usuarios = repository.findAll();
        List<UsuarioSaida> medicos = new ArrayList<>();
        for(UsuarioEntity usuarioEntity : usuarios){
            UsuarioSaida usuario = new UsuarioSaida();
            usuario.setIdUsuario(usuarioEntity.getIdUsuario());
            usuario.setGenero(usuarioEntity.getGenero().getIdGenero());
            usuario.setEspMedica(usuarioEntity.getEspMed().getIdEspMed());
            usuario.setUfCrm(usuarioEntity.getUf().getIdUf());
            usuario.setTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
            usuario.setNmNome(usuarioEntity.getNome());
            usuario.setDtNascimento(usuarioEntity.getDtNascimento());
            usuario.setNrCpf(usuarioEntity.getNrCpf());
            usuario.setNrCrm(usuarioEntity.getNrCrm());

            medicos.add(usuario);
        }
        return medicos;
    }

    public UsuarioEntity consultarPorCpf(String nrCpf){
        return repository.findByNrCpf(nrCpf);
    }


    @Transactional
    public String alterar(Usuario usuario, BigInteger id){

        UsuarioEntity entity= repository.findById(id).get();

        EspMedEntity espEntity = especialidadeRepository.findById(usuario.getIdEspMed().getIdEspMed()).get();
        entity.setEspMed(espEntity);

        UfEntity ufEntity = ufRepository.findById(usuario.getUf().getIdUf()).get();
        entity.setUf(ufEntity);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(2)).get();
        entity.setTipoUsuario(tipoUsuarioEntity);

        entity.setNome(usuario.getNome());
        entity.setDtNascimento(usuario.getDtNascimento());
        entity.setNrCpf(usuario.getNrCpf());
        entity.setNrCrm(usuario.getNrCrm());

        PrecoEntity precoEntity = new PrecoEntity();
        Preco preco = usuario.getPreco();
        precoEntity.setVlConsulta(preco.getVlConsulta());
        entity.setPreco(precoEntity);

        List<EnderecoEntity> enderecosEntity = entity.getEnderecos();
        for(Endereco endereco : usuario.getEnderecos()){
            EnderecoEntity enderecoEntity = new EnderecoEntity();
            enderecoEntity.setIdCidade(endereco.getIdCidade());
            enderecoEntity.setDsComplemento(endereco.getDsComplemento());
            enderecoEntity.setDsEndereco(endereco.getDsEndereco());
            enderecoEntity.setDsBairro(endereco.getDsBairro());
            enderecoEntity.setNrCep(endereco.getNrCep());
        }
        entity.setEnderecos(enderecosEntity);

        List<ContatoEntity> contatosEntity = entity.getContatos();
        for(Contato contato : usuario.getContatos()){
            ContatoEntity contatoEntity = new ContatoEntity();
            TipoContatoEntity tpContatoEntity = tipoContatoRepository.findById(BigInteger.valueOf(2)).get();
            contatoEntity.setTipoContato(tpContatoEntity);
            contatoEntity.setDsContato(contato.getDsContato());
        }
        entity.setContatos(contatosEntity);

        repository.save(entity);

        return "Alteração realizado com sucesso";
    }

    @Transactional
    public String cadastrarMedico(Usuario usuario){

        UsuarioEntity entity = new UsuarioEntity();

        EspMedEntity espEntity = especialidadeRepository.findById(usuario.getIdEspMed().getIdEspMed()).get();
        entity.setEspMed(espEntity);

        UfEntity ufEntity = ufRepository.findById(usuario.getUf().getIdUf()).get();
        entity.setUf(ufEntity);

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(BigInteger.valueOf(2)).get();
        entity.setTipoUsuario(tipoUsuarioEntity);

        entity.setNome(usuario.getNome());
        entity.setDtNascimento(usuario.getDtNascimento());
        entity.setNrCpf(usuario.getNrCpf());
        entity.setNrCrm(usuario.getNrCrm());

        PrecoEntity precoEntity = new PrecoEntity();
        Preco preco = usuario.getPreco();
        precoEntity.setVlConsulta(preco.getVlConsulta());
        entity.setPreco(precoEntity);

        List<EnderecoEntity> enderecosEntity = new ArrayList<>();
        for(Endereco endereco : usuario.getEnderecos()){
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
        for(Contato contato : usuario.getContatos()){
            ContatoEntity contatoEntity = new ContatoEntity();
            TipoContatoEntity tpContatoEntity = tipoContatoRepository.findById(BigInteger.valueOf(2)).get();
            contatoEntity.setTipoContato(tpContatoEntity);
            contatoEntity.setDsContato(contato.getDsContato());

            contatosEntity.add(contatoEntity);
        }

        entity.setContatos(contatosEntity);
        repository.save(entity);

        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        LoginUsuario loginUsuario = usuario.getLogin();

        BigInteger novoId = entity.getIdUsuario();
        loginUsuarioEntity.setIdUsuario(novoId);
        loginUsuarioEntity.setDsEmail(loginUsuario.getDsEmail());
        loginUsuarioEntity.setDsSenha(loginUsuario.getDsSenha());

        loginUsuarioRepository.save(loginUsuarioEntity);

        return "Usuário cadastrado com sucesso";
    }
}