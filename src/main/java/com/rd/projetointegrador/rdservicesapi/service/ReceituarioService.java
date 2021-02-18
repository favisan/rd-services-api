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
@Service
public class ReceituarioService {
    //Repository
    @Autowired private ReceituarioRepository receituarioRepository;
    @Autowired private ProntuarioRepository prontuarioRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private TipoReceitaRepository tipoReceitaRepository;
    @Autowired private AtendimentoRepository atendimentoRepository;
    @Autowired private AgPacienteRepository agPacienteRepository;

    //Service
    @Autowired private TipoReceitaService tipoReceitaService;
    @Autowired private FormaFarmacService formaFarmacService;
    @Autowired private ViaAdmService viaAdmService;
    @Autowired private MedicacaoService medicacaoService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private EnderecoService enderecoService;
    @Autowired private MedicoService medicoService;

    //Consultar receituário por Id
    public ReceituarioInput exibirReceituarioPorId(BigInteger idReceituario){
        ReceituarioEntity receituarioEntity = receituarioRepository.findById(idReceituario).get();
        ReceituarioInput receituario = new ReceituarioInput();
        receituario = converterReceituarioToDTO(receituarioEntity, receituario);
        return receituario;
    }
    //Consultar todos os receituários vinculados a um mesmo Id de prontuário
    public List<ReceituarioInput> listarReceituarioPorIdProntuario(BigInteger idProntuario){
        //Buscando A Entity Prontuario por Id
        ProntuarioEntity prontuarioEntity= prontuarioRepository.findById(idProntuario).get();
        //Buscando A Lista de Entity Receituário por Id de Prontuário
        List <ReceituarioEntity> receituariosEntity = receituarioRepository.findByProntuario(prontuarioEntity);
        //Convertendo a Lista Entity Receituario para Lista DTO
        List<ReceituarioInput> receituarios = new ArrayList<>();
        receituarios = converterReceituariosToDTO(receituariosEntity,receituarios);
        return receituarios;
    }
    //Inserir um receituário
    @Transactional
    public String inserirReceituario(ReceituarioInput receituario) {
        //Convertendo a DTO Receituario para Entity
        ReceituarioEntity  receituarioEntity= new ReceituarioEntity();
        receituarioEntity = converterReceituarioToEntity(receituario, receituarioEntity);
        //INSERINDO A ENTITY Receituário no BD
        receituarioRepository.save(receituarioEntity);
        return "Receituário inserido com sucesso!";
    }
    //Preenchendo a tela Receituário com listas para os selects do front e dados que são fixos na tela
    public ReceituarioOutput preencherReceituario(BigInteger idMedico, BigInteger idPaciente, BigInteger idAgPaciente){
        ReceituarioOutput receituarioOutput = new ReceituarioOutput();

        AgPacienteEntity agPacienteEntity = agPacienteRepository.findById(idAgPaciente).get();

        AtendimentoEntity atendimentoEntity = atendimentoRepository.findByAgPaciente(agPacienteEntity);

        BigInteger idProntuario = atendimentoEntity.getProntuario().getIdProntuario();

        receituarioOutput.setIdProntuario(idProntuario);
        receituarioOutput.setListaTipoReceita(tipoReceitaService.listarTiposDeReceita());
        receituarioOutput.setNomePaciente(usuarioService.getUsuario(idPaciente).getNmNome());
        receituarioOutput.setListaMedicacao(medicacaoService.listarMedicacoes());
        receituarioOutput.setListaViaAdm(viaAdmService.listarViasAdm());
        receituarioOutput.setListaFormaFarmac(formaFarmacService.listarFormasFarmac());
        receituarioOutput.setMedico(usuarioService.getMedico(idMedico));
        return receituarioOutput;
    }
    //Convertendo de Entity para DTO
    public ReceituarioInput converterReceituarioToDTO(ReceituarioEntity receituarioEntity, ReceituarioInput receituario) {
        //PEGAR A DTO Usuario paciente
        Usuario paciente = new Usuario();
        paciente = usuarioService.conversaoUsuarioDTO(receituarioEntity.getPaciente(), paciente);
        //PEGAR A DTO Prontuario
        Prontuario prontuario = new Prontuario();
        prontuario.setIdProntuario(receituarioEntity.getProntuario().getIdProntuario());
        prontuario.setDsSubjetivo(receituarioEntity.getProntuario().getDsSubjetivo());
        prontuario.setDsObjetivo(receituarioEntity.getProntuario().getDsObjetivo());
        prontuario.setDsAvaliacao(receituarioEntity.getProntuario().getDsAvaliacao());
        prontuario.setDsPlano(receituarioEntity.getProntuario().getDsPlano());
        prontuario.setDsObservacoes(receituarioEntity.getProntuario().getDsObservacoes());
        //PEGAR A DTO OutputMedico medico
        OutputMedico medico = new OutputMedico();
        medico = medicoService.conversaoOutputMedicoDTO(receituarioEntity.getMedico(), medico);
        //PEGAR A DTO Tipo de receita
        TipoReceita tipoReceita = new TipoReceita();
        tipoReceita.setIdTipoReceita(receituarioEntity.getTipoReceita().getIdTipoReceita());
        tipoReceita.setDsTipoReceita(receituarioEntity.getTipoReceita().getDsTipoReceita());
        //PEGAR A DTO Lista de prescricoes
        List<Prescricao> prescricoes = new ArrayList<>();
        for (PrescricaoEntity prescricaoEntity : receituarioEntity.getPrescricoes()) {
            Prescricao prescricao = new Prescricao();
            prescricao.setIdPrescricao(prescricaoEntity.getIdPrescricao());
            prescricao.setIdMedicacao(prescricaoEntity.getIdMedicacao());
            prescricao.setIdFormaFarmac(prescricaoEntity.getIdFormaFarmac());
            prescricao.setIdViaAdm(prescricaoEntity.getIdViaAdm());
            prescricao.setVlQuantidade(prescricaoEntity.getVlQuantidade());
            prescricao.setVlConcentracao(prescricaoEntity.getVlConcentracao());
            prescricao.setDsOrientacoes(prescricaoEntity.getDsOrientacoes());
            prescricoes.add(prescricao);
        }
        //SETANDO OS VALORES NA DTO Receituario
        receituario.setIdReceituario(receituarioEntity.getIdReceituario());
        receituario.setPaciente(paciente);
        receituario.setProntuario(prontuario);
        receituario.setMedico(medico);
        receituario.setTipoReceita(tipoReceita);
        receituario.setDtEmissao(receituarioEntity.getDtEmissao());
        receituario.setDsEndImgAssMed(receituarioEntity.getDsEndImgAssMed());
        receituario.setPrescricoes(prescricoes);
        return receituario;
    }
    //Convertendo listaEntity para ListaDTO
    public List<ReceituarioInput> converterReceituariosToDTO(List<ReceituarioEntity> receituariosEntity, List<ReceituarioInput> receituarios) {
        for(ReceituarioEntity receituarioEntity : receituariosEntity) {
            ReceituarioInput receituario = new ReceituarioInput();
            receituario = converterReceituarioToDTO(receituarioEntity,receituario);
            receituarios.add(receituario);
        }
        return receituarios;
    }
    //Convertendo de DTO para Entity
    public ReceituarioEntity converterReceituarioToEntity(ReceituarioInput receituario, ReceituarioEntity receituarioEntity) {
        //PEGAR A ENTITY USUARIO Paciente
        BigInteger idPaciente = receituario.getPaciente().getIdUsuario();
        UsuarioEntity pacienteEntity = usuarioRepository.findById(idPaciente).get();
        //PEGAR A ENTITY Prontuario
        BigInteger idProntuario = receituario.getProntuario().getIdProntuario();
        ProntuarioEntity prontuarioEntity = prontuarioRepository.findById(idProntuario).get();
        //PEGAR A ENTITY USUARIO Medico
        BigInteger idMedico = receituario.getMedico().getIdUsuario();
        UsuarioEntity medicoEntity = usuarioRepository.findById(idMedico).get();
        //PEGAR A ENTITY Tipo de receita
        BigInteger idTipoReceita = receituario.getTipoReceita().getIdTipoReceita();
        TipoReceitaEntity tipoReceitaEntity = tipoReceitaRepository.findById(idTipoReceita).get();
        //PEGAR A ENTITY Lista de prescricoes
        List<PrescricaoEntity> prescricoesEntity = new ArrayList<>();
        for(Prescricao prescricao : receituario.getPrescricoes()){
            PrescricaoEntity prescricaoEntity = new PrescricaoEntity();
            prescricaoEntity.setIdMedicacao(prescricao.getIdMedicacao());
            prescricaoEntity.setIdFormaFarmac(prescricao.getIdFormaFarmac());
            prescricaoEntity.setIdViaAdm(prescricao.getIdViaAdm());
            prescricaoEntity.setVlQuantidade(prescricao.getVlQuantidade());
            prescricaoEntity.setVlConcentracao(prescricao.getVlConcentracao());
            prescricaoEntity.setDsOrientacoes(prescricao.getDsOrientacoes());
            prescricoesEntity.add(prescricaoEntity);
        }
        //SETANDO OS VALORES NA ENTITY Receituario
        receituarioEntity.setPaciente(pacienteEntity);
        receituarioEntity.setProntuario(prontuarioEntity);
        receituarioEntity.setMedico(medicoEntity);
        receituarioEntity.setTipoReceita(tipoReceitaEntity);
        receituarioEntity.setDtEmissao(receituario.getDtEmissao());
        receituarioEntity.setDsEndImgAssMed(receituario.getDsEndImgAssMed());
        receituarioEntity.setPrescricoes(prescricoesEntity);
        return receituarioEntity;
    }
}