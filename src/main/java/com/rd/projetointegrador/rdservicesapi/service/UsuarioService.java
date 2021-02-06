package com.rd.projetointegrador.rdservicesapi.service;


import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


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
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private LoginUsuarioService loginUsuarioService;

    //BUSCAR MEDICO POR ID
    public OutputMedico getMedico(BigInteger id) {
        System.out.println("ID: " + id);
        UsuarioEntity entity = repository.findById(id).get();
        OutputMedico user = new OutputMedico();
        user.setIdUsuario(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNrCrm(entity.getNrCrm());

        UfEntity ufEntity = entity.getUf();
        Uf uf = new Uf();
        uf.setDsUf(ufEntity.getDsUf());
        user.setUfCrm(uf);

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

    //BUSCAR CPF PARA IMPEDIR CADATRO COM MESMO CPF
    public UsuarioEntity consultarPorCpf(String nrCpf) {
        return repository.findByNrCpf(nrCpf);
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

        entity.setNome(inputMedico.getNome());
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

        entity.setNome(inputMedico.getNome());
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
    public PerfilMedico mostrarTelaPerfil(BigInteger idMedico, BigInteger idUf) {
        PerfilMedico perfilMedico = new PerfilMedico();
        perfilMedico.setMedico(getMedico(idMedico));
        perfilMedico.setDsEmail(loginUsuarioRepository.findOneByIdUsuario(idMedico).getDsEmail());
        perfilMedico.setCidades(cidadeService.buscarCidadePorUf(idUf));
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
