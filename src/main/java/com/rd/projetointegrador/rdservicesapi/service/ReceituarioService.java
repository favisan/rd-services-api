package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Prescricao;
import com.rd.projetointegrador.rdservicesapi.dto.Receituario;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.ProntuarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.ReceituarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoReceitaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ReceituarioEntity exibirReceituarioPorId(BigInteger idReceituario){

        ReceituarioEntity receituario = receituarioRepository.findById(idReceituario).get();
        return receituario;

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

}
