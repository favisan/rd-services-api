package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    //GRUPO1

    //repositories
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private GeneroRepository generoRepository;
    @Autowired private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired private LoginUsuarioRepository loginUsuarioRepository;
    @Autowired private ContratoRepository contratoRepository;
    @Autowired private CartaoRepository cartaoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private TipoContatoRepository tipoContatoRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private PlanosRepository planosRepository;

    //services
    @Autowired private UfService ufService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private GeneroService generoService;
    @Autowired private PlanosService planosService;
    @Autowired private ContratoService contratoService;
    @Autowired private LoginUsuarioService luService;
    @Autowired private CartaoService cartaoService;
    @Autowired private LembreteService lembreteService;
    @Autowired private EnderecoService enderecoService;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ResponseEntity cadastrarCliente(InputCliente inputUsuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        LoginUsuarioEntity loginUsuarioEntity = new LoginUsuarioEntity();
        ContratoEntity contratoEntity = new ContratoEntity();
        CartaoEntity cartaoEntity= new CartaoEntity();

        try {

            //VALIDAR CPF
            String cpf = inputUsuario.getUsuario().getNrCpf();
            List<UsuarioEntity> usuarioExistente = usuarioRepository.findByNrCpf(cpf);
            System.out.println(usuarioExistente.isEmpty());

            //VALIDAR E-MAIL
            String email = inputUsuario.getLoginUsuario().getDsEmail();
            List<LoginUsuarioEntity> loginExistente = loginUsuarioRepository.findByDsEmail(email);
            System.out.println(loginExistente.isEmpty());

        if(usuarioExistente.isEmpty() && loginExistente.isEmpty()) {
            //Passando dados do Usuário
            usuarioEntity = usuarioService.conversaoUsuarioEntity(inputUsuario.getUsuario(), usuarioEntity);
            usuarioEntity = usuarioRepository.save(usuarioEntity);
            BigInteger novoId = usuarioEntity.getIdUsuario();

            //Entidade LoginUsuario
            inputUsuario.getLoginUsuario().setIdUsuario(novoId);
            loginUsuarioEntity = luService.conversaoLoginUsuarioEntity(inputUsuario.getLoginUsuario(), loginUsuarioEntity);
            loginUsuarioRepository.save(loginUsuarioEntity);
            System.out.println("Inseriu Login: " + loginUsuarioEntity.getDsEmail());

            //Entidade Contrato
            inputUsuario.getContrato().setIdUsuario(novoId);
            contratoEntity = contratoService.conversaoContratoEntity(inputUsuario.getContrato(), contratoEntity);
            contratoEntity.setDsContrato("Contrato: " + contratoEntity.getPlanosEntity().getNmPlano());
            contratoEntity.setDtVigencia(java.util.Calendar.getInstance().getTime());
            contratoRepository.save(contratoEntity);
            System.out.println("Inseriu Contrato: " + contratoEntity.getIdContrato());

            //Entidade Cartao
            if(!inputUsuario.getCartao().getNrCartao().equals("") && inputUsuario.getCartao().getCodSeguranca() != null) {
                System.out.println("Entrou no if!");
                inputUsuario.getCartao().getUsuario().setIdUsuario(novoId);
                cartaoEntity = cartaoService.conversaoCartaoEntity(inputUsuario.getCartao(), cartaoEntity);
                cartaoRepository.save(cartaoEntity);
                System.out.println("Inseriu Cartão: " + cartaoEntity.getIdCartao());
            }

            //Entidade Contato
            ContatoEntity contatoEntity = new ContatoEntity();
            contatoEntity.setIdUsuario(novoId);
            contatoEntity.setNrDdd(inputUsuario.getDdd());
            contatoEntity.setDsContato(inputUsuario.getCelular());
            contatoEntity.setTipoContato(tipoContatoRepository.findById(new BigInteger("3")).get());
            contatoRepository.save(contatoEntity);
            System.out.println("Inseriu Contato: " + contatoEntity.getIdContato());

            ResultData resultData = new ResultData(HttpStatus.OK.value(), "Usuário cadastrado com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(resultData);

        } else {
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Usuário já cadastrado!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

        } catch (Exception e) {
            System.out.println(e);
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro ao cadastrar usuário.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    @Transactional
    public ResponseEntity alterarCliente(BigInteger idUsuario, InputCliente inputUsuario){

        BigInteger id = idUsuario;

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByIdUsuario(id);
        List<ContratoEntity> contratoEntities = contratoRepository.findByUsuarioOrderByDtVigenciaDesc(usuarioEntity);
        ContratoEntity contratoEntity = contratoEntities.get(0);
        CartaoEntity cartaoEntity= cartaoRepository.findById(inputUsuario.getCartao().getIdCartao()).get();

        List<ContatoEntity> contatoEntities = contatoRepository.findByIdUsuario(id);
        ContatoEntity contatoEntity = contatoEntities.get(0);

        try {
                //Passando dados do Usuário
                usuarioEntity = usuarioService.conversaoUsuarioEntity(inputUsuario.getUsuario(), usuarioEntity);
                usuarioEntity = usuarioRepository.save(usuarioEntity);
            System.out.println("Alterou Usuário: " + usuarioEntity.getIdUsuario());

                //Entidade LoginUsuario
                loginUsuarioEntity = luService.conversaoLoginUsuarioEntity(inputUsuario.getLoginUsuario(), loginUsuarioEntity);
                loginUsuarioRepository.save(loginUsuarioEntity);
                System.out.println("Alterou Login: " + loginUsuarioEntity.getDsEmail());

                //Entidade Contrato
                if(contratoEntity.getPlanosEntity().getIdPlano() != inputUsuario.getContrato().getPlano().getIdPlano()) {
                    System.out.println(inputUsuario.getContrato().getPlano().getIdPlano());
                    System.out.println("Antigo Contrato: " + contratoEntity.getIdContrato() + " / " + contratoEntity.getPlanosEntity().getIdPlano());
                    ContratoEntity newContratoEntity = new ContratoEntity();
                    inputUsuario.getContrato().setIdUsuario(usuarioEntity.getIdUsuario());

                    newContratoEntity = contratoService.conversaoContratoEntity(inputUsuario.getContrato(), newContratoEntity);
                    newContratoEntity.setDsContrato("Contrato: " + newContratoEntity.getPlanosEntity().getNmPlano());
                    newContratoEntity.setDtVigencia(java.util.Calendar.getInstance().getTime());
                    contratoRepository.save(newContratoEntity);

                    System.out.println("Alterou Contrato: " + newContratoEntity.getIdContrato() + " / " + newContratoEntity.getPlanosEntity().getIdPlano());
                }

                //Entidade Cartao
                if(!cartaoEntity.getNrCartao().equals(inputUsuario.getCartao().getNrCartao())) {
                    System.out.println(cartaoEntity.getNrCartao());
                    CartaoEntity newCartaoEntity = new CartaoEntity();
                    newCartaoEntity = cartaoService.conversaoCartaoEntity(inputUsuario.getCartao(), newCartaoEntity);
                    cartaoRepository.save(newCartaoEntity);

                    System.out.println("Inseriu novo Cartão: " + newCartaoEntity.getNrCartao());
                    System.out.println("Inseriu novo Cartão: " + newCartaoEntity.getIdCartao());
                } else {
                    cartaoEntity = cartaoService.conversaoCartaoEntity(inputUsuario.getCartao(), cartaoEntity);
                    cartaoRepository.save(cartaoEntity);
                }

                //Entidade Contato
                contatoEntity.setNrDdd(inputUsuario.getDdd());
                contatoEntity.setDsContato(inputUsuario.getCelular());
                contatoEntity.setTipoContato(tipoContatoRepository.findById(new BigInteger("3")).get());
                contatoRepository.save(contatoEntity);
                System.out.println("Alterou Contato: " + contatoEntity.getIdContato());

                ResultData resultData = new ResultData(HttpStatus.OK.value(), "Usuário atualizado com sucesso");
                return ResponseEntity.status(HttpStatus.OK).body(resultData);


        } catch (Exception e) {
            System.out.println(e);
            ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro ao atualizar usuário.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    //TELA DE CADASTRO - GET
    public FormularioCadastro getFormularioCadastro() {
        FormularioCadastro formularioCadastro = new FormularioCadastro();

        formularioCadastro.setUfs(ufService.getUfsDTO());
        formularioCadastro.setGenero(generoService.getGenerosDTO());
        formularioCadastro.setPlanos(planosService.getPlanosDTO());
        return formularioCadastro;
    }

    //TELA MEUS DADOS - GET
    public FormularioMeusDados getFormularioMeusDados(BigInteger id) {
        FormularioCadastro formularioCadastro = getFormularioCadastro();
        FormularioMeusDados formularioMeusDados = new FormularioMeusDados();

        Boolean teste = usuarioRepository.existsById(id);
        if(teste) {

            InputCliente inputCliente = getInputClienteDTO(id);
            formularioMeusDados.setInputCliente(inputCliente);
            formularioMeusDados.setUfs(formularioCadastro.getUfs());
            formularioMeusDados.setGenero(formularioCadastro.getGenero());
            formularioMeusDados.setPlanos(formularioCadastro.getPlanos());

            return formularioMeusDados;
        }
            return null;
    }

    public AreaDoCliente getAreaDoCliente(BigInteger idUsuario){
        AreaDoCliente areaDoCliente = new AreaDoCliente();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).get();


        ContratoEntity contratoEntitiy = contratoRepository.findOneByUsuario(usuarioEntity);
        PlanosEntity planosEntity = contratoEntitiy.getPlanosEntity();
        Planos plano = new Planos();
        plano = planosService.conversaoPlanoDTO(planosEntity, plano);

        areaDoCliente.setIdPaciente(usuarioEntity.getIdUsuario());
        areaDoCliente.setNmNome( usuarioEntity.getNmNome());
        areaDoCliente.setPlano(plano);

        List<Lembrete> lembretes = lembreteService.getLembretesIdPaciente(idUsuario);
        areaDoCliente.setLembretes(lembretes);

        return areaDoCliente;
    }

    public InputCliente getInputClienteDTO(BigInteger id) {
        InputCliente inputCliente = new InputCliente();

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        LoginUsuarioEntity loginUsuarioEntity = loginUsuarioRepository.findOneByIdUsuario(id);
        List<ContatoEntity> contatoEntities = contatoRepository.findByIdUsuario(id);
        List<ContratoEntity> contratoEntities = contratoRepository.findByUsuario(usuarioEntity);
        List<CartaoEntity> cartaoEntities = cartaoRepository.findByUsuario(usuarioEntity);

        Usuario usuario = new Usuario();
        usuario = usuarioService.conversaoUsuarioDTO(usuarioEntity, usuario);

        LoginUsuario loginUsuario = new LoginUsuario();
        loginUsuario = luService.conversaoLoginUsuarioDTO(loginUsuarioEntity, loginUsuario);

        Contrato contrato = new Contrato();
        contrato = contratoService.conversaoContratoDTO(contratoEntities.get(0), contrato);

        Cartao cartao = new Cartao();
        cartao = cartaoService.conversaoCartaoDTO(cartaoEntities.get(0), cartao);

        inputCliente.setUsuario(usuario);
        inputCliente.setLoginUsuario(loginUsuario);
        inputCliente.setContrato(contrato);
        inputCliente.setCartao(cartao);
        inputCliente.setDdd(contatoEntities.get(0).getNrDdd());
        inputCliente.setCelular(contatoEntities.get(0).getDsContato());

        return inputCliente;
    }

}
