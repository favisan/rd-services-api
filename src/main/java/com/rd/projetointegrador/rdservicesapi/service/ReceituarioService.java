package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.ProntuarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.ReceituarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoReceitaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceituarioService {

    @Autowired
    private ReceituarioRepository receituarioRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    @Autowired
    private TipoReceitaService tipoReceitaService;

    @Autowired
    private FormaFarmacService formaFarmacService;

    @Autowired
    private ViaAdmService viaAdmService;

    @Autowired
    private MedicacaoService medicacaoService;

    @Autowired
    private UsuarioService usuarioService;

    public ReceituarioEntity exibirReceituarioPorId(BigInteger idReceituario){

        ReceituarioEntity receituario = receituarioRepository.findById(idReceituario).get();
        return receituario;

    }

    public List<Receituario> listarReceituarioPorIdProntuario(BigInteger idProntuario){

        ProntuarioEntity prontuarioEntity= prontuarioRepository.findById(idProntuario).get();
        List <ReceituarioEntity> receituariosEntity = receituarioRepository.findByProntuario(prontuarioEntity);

        List<Receituario> receituarios = new ArrayList<>();

        for(ReceituarioEntity receituarioEntity : receituariosEntity) {
            Receituario receituario = new Receituario();
            receituario.setIdReceituario(receituarioEntity.getIdReceituario());

            InputMedico paciente = new InputMedico();
            paciente.setIdUsuario(receituarioEntity.getPaciente().getIdUsuario());
            paciente.setNome(receituarioEntity.getPaciente().getNome());

            receituario.setPaciente(paciente);

            Prontuario prontuario = new Prontuario();
            prontuario.setIdProntuario(receituarioEntity.getProntuario().getIdProntuario());
            prontuario.setDsSubjetivo(receituarioEntity.getProntuario().getDsSubjetivo());
            prontuario.setDsObjetivo(receituarioEntity.getProntuario().getDsObjetivo());
            prontuario.setDsAvaliacao(receituarioEntity.getProntuario().getDsAvaliacao());
            prontuario.setDsPlano(receituarioEntity.getProntuario().getDsPlano());
            prontuario.setDsObservacoes(receituarioEntity.getProntuario().getDsObservacoes());

            receituario.setProntuario(prontuario);

            InputMedico medico = new InputMedico();
            medico.setIdUsuario(receituarioEntity.getMedico().getIdUsuario());
            medico.setNome(receituarioEntity.getMedico().getNome());

            receituario.setMedico(medico);

            TipoReceita tipoReceita = new TipoReceita();
            tipoReceita.setIdTipoReceita(receituarioEntity.getTipoReceita().getIdTipoReceita());
            tipoReceita.setDsTipoReceita(receituarioEntity.getTipoReceita().getDsTipoReceita());

            receituario.setTipoReceita(tipoReceita);

            receituario.setDtEmissao(receituarioEntity.getDtEmissao());
            receituario.setDsEndImgAssMed(receituarioEntity.getDsEndImgAssMed());

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

            receituario.setPrescricoes(prescricoes);

            receituarios.add(receituario);
        }

        return receituarios;

    }

    @Transactional
    public String inserirReceituario(Receituario receituario) {

        ReceituarioEntity  receituarioEntity= new ReceituarioEntity();

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

        receituarioEntity.setPaciente(pacienteEntity);
        receituarioEntity.setProntuario(prontuarioEntity);
        receituarioEntity.setMedico(medicoEntity);
        receituarioEntity.setTipoReceita(tipoReceitaEntity);
        receituarioEntity.setDtEmissao(receituario.getDtEmissao());
        receituarioEntity.setDsEndImgAssMed(receituario.getDsEndImgAssMed());
        receituarioEntity.setPrescricoes(prescricoesEntity);

        receituarioRepository.save(receituarioEntity);

        return "Receituário inserido com sucesso!";

    }

    public ReceituarioOutput preencherReceituario(BigInteger idMedico, BigInteger idPaciente){
        ReceituarioOutput receituarioOutput = new ReceituarioOutput();
        receituarioOutput.setListaTipoReceita(tipoReceitaService.listarTiposDeReceita());
        //receituarioOutput.setNomePaciente(usuarioService.getUsuario(idPaciente))
        receituarioOutput.setListaMedicacao(medicacaoService.listarMedicacoes());
        receituarioOutput.setListaViaAdm(viaAdmService.listarViasAdm());
        receituarioOutput.setListaFormaFarmac(formaFarmacService.listarFormasFarmac());
        receituarioOutput.setMedico(usuarioService.getMedico(idMedico));

        return receituarioOutput;

    }

}
