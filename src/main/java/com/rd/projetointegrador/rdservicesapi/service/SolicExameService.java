package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.*;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.ProntuarioRepository;
import com.rd.projetointegrador.rdservicesapi.repository.SolicExameRepository;
import com.rd.projetointegrador.rdservicesapi.repository.TipoExameRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
public class SolicExameService {

    @Autowired
    private SolicExameRepository repository;

    @Autowired
    private TipoExameRepository tipoRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private UsuarioService usuarioService;


    public String inserirSolicExame(SolicExame solicExame) {

        SolicExameEntity entity = new SolicExameEntity();

        BigInteger idProntuario = solicExame.getProntuario().getIdProntuario();
        ProntuarioEntity prontuarioEntity = prontuarioRepository.findById(idProntuario).get();

        BigInteger idMedico = solicExame.getMedico().getIdUsuario();
        UsuarioEntity medicoEntity = usuarioRepository.findById(idMedico).get();

        BigInteger idPaciente = solicExame.getPaciente().getIdUsuario();
        UsuarioEntity pacienteEntity = usuarioRepository.findById(idPaciente).get();

        entity.setDtSolicitacao(solicExame.getDtSolicitacao());
        entity.setDsIndicacaoClin(solicExame.getDsIndicacaoClin());
        entity.setProntuario(prontuarioEntity);
        entity.setPaciente(pacienteEntity);
        entity.setMedico(medicoEntity);

        List<TipoExameEntity> tipoExameEntities = new ArrayList<>();

        for (TipoExame tipo : solicExame.getExames()) {

            BigInteger tipoExameId = tipo.getIdTipoExame();
            TipoExameEntity exameEntity = tipoRepository.findById(tipoExameId).get();
            tipoExameEntities.add(exameEntity);
        }

        entity.setExames(tipoExameEntities);

        repository.save(entity);

        return "Cadastro realizado com sucesso!";
    }

    public List<SolicExame> listarSolicExamePorIdProntuario(BigInteger idProntuario) {

      ProntuarioEntity prontuario = prontuarioRepository.findById(idProntuario).get();
      List<SolicExameEntity> solicitacoes = repository.findByProntuario(prontuario);

        List<SolicExame> lista = new ArrayList<>();

        for(SolicExameEntity solic : solicitacoes){
            SolicExame solicitacao = new SolicExame();
            solicitacao.setIdSolicExame(solic.getIdSolicExame());
            solicitacao.setDtSolicitacao(solic.getDtSolicitacao());
            solicitacao.setDsIndicacaoClin(solic.getDsIndicacaoClin());

            Prontuario pront = new Prontuario();
            pront.setIdProntuario(solic.getProntuario().getIdProntuario());
            pront.setDsSubjetivo(solic.getProntuario().getDsSubjetivo());
            pront.setDsObjetivo(solic.getProntuario().getDsObjetivo());
            pront.setDsAvaliacao(solic.getProntuario().getDsAvaliacao());
            pront.setDsPlano(solic.getProntuario().getDsPlano());
            pront.setDsObservacoes(solic.getProntuario().getDsObservacoes());

            solicitacao.setProntuario(pront);

            InputMedico paciente = new InputMedico();
            paciente.setIdUsuario(solic.getPaciente().getIdUsuario());
            paciente.setNome(solic.getPaciente().getNmNome());

            solicitacao.setPaciente(paciente);

            InputMedico medico = new InputMedico();
            medico.setIdUsuario(solic.getMedico().getIdUsuario());
            medico.setNome(solic.getMedico().getNmNome());

            List<TipoExame> exam = new ArrayList<>();

            for (TipoExameEntity exame : solic.getExames()) {

                TipoExame tipo = new TipoExame();
                tipo.setIdTipoExame(exame.getIdTipoExame());
                tipo.setDsTipoExame(exame.getDsTipoExame());
                exam.add(tipo);
            }

            solicitacao.setExames(exam);
            solicitacao.setMedico(medico);
            lista.add(solicitacao);
        }

        return lista;
    }

    public SolicExameEntity exibirSolicExamePorIdSolicitacao(BigInteger idSolicExame){

        SolicExameEntity solicitacao = repository.findById(idSolicExame).get();

        return solicitacao;

    }

    //FORMULÁRIO INICIAL
    public SolicExameOutput preencherSolicitacaoInicial(BigInteger idMedico){
        SolicExameOutput output = new SolicExameOutput();
        output.setListaTipoExame(tipoRepository.findAll());
        output.setMedico(usuarioService.getMedico(idMedico));
        //output.setNomePaciente(....);

        return output;

    }






//  MÉTODO PARA LISTAR É SÓ PARA TESTE
//    public List<SolicExame> listarSolicExame() {
//        List<SolicExameEntity> exames = repository.findAll();
//        List<SolicExame> listaExame = new ArrayList<>();
//
//        for(SolicExameEntity solic : exames){
//            SolicExame s = new SolicExame();
//            s.setIdSolicExame(solic.getIdSolicExame());
//            s.setDtSolicitacao(solic.getDtSolicitacao());
//            s.setDsIndicacaoClin(solic.getDsIndicacaoClin());
//
//            Prontuario p = new Prontuario();
//            p.setIdProntuario(solic.getProntuario().getIdProntuario());
//            p.setDsSubjetivo(solic.getProntuario().getDsSubjetivo());
//            p.setDsObjetivo(solic.getProntuario().getDsObjetivo());
//            p.setDsAvaliacao(solic.getProntuario().getDsAvaliacao());
//            p.setDsPlano(solic.getProntuario().getDsPlano());
//            p.setDsObservacoes(solic.getProntuario().getDsObservacoes());
//
//            s.setProntuario(p);
//
//            InputMedico paciente = new InputMedico();
//            paciente.setIdUsuario(solic.getPaciente().getIdUsuario());
//            paciente.setNome(solic.getPaciente().getNome());
//
//            s.setPaciente(paciente);
//
//            InputMedico medico = new InputMedico();
//            medico.setIdUsuario(solic.getMedico().getIdUsuario());
//            medico.setNome(solic.getMedico().getNome());
//
//            List<TipoExame> exam = new ArrayList<>();
//
//            for (TipoExameEntity e : solic.getExames()) {
//                TipoExame tipo = new TipoExame();
//                tipo.setIdTipoExame(e.getIdTipoExame());
//                tipo.setDsTipoExame(e.getDsTipoExame());
//                exam.add(tipo);
//            }
//
//            s.setExames(exam);
//            s.setMedico(medico);
//            listaExame.add(s);
//        }
//        return listaExame;
//    }

}
