package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Medicacao;
import com.rd.projetointegrador.rdservicesapi.entity.MedicacaoEntity;
import com.rd.projetointegrador.rdservicesapi.repository.MedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicacaoService {

    //Repository
    @Autowired
    private MedicacaoRepository medicacaoRepository;

    //Listar todas as medicações
    public List<Medicacao> listarMedicacoes() {

        //Buscando Toda A Lista de Entity Medicacao
        List<MedicacaoEntity> medicacoesEntity = medicacaoRepository.findAll();

        //Convertendo a Lista Entity Medicacao para Lista DTO
        List<Medicacao> medicacoes = new ArrayList<>();
        medicacoes = converterMedicacoesDTO(medicacoesEntity, medicacoes);

        return medicacoes;

    }

    //Convertendo de Entity para DTO
    public Medicacao converterTipoReceitaDTO(MedicacaoEntity medicacaoEntity, Medicacao medicacao) {

        //SETANDO OS VALORES NA DTO Medicacao
        medicacao.setIdMedicacao(medicacaoEntity.getIdMedicacao());
        medicacao.setDsMedicacao(medicacaoEntity.getDsMedicacao());

        return medicacao;
    }

    //Convertendo lista Entity para ListaDTO
    public List<Medicacao> converterMedicacoesDTO(List<MedicacaoEntity> medicacoesEntity, List<Medicacao> medicacoes) {

        for(MedicacaoEntity medicacaoEntity : medicacoesEntity) {
            Medicacao medicacao = new Medicacao();
            medicacao = converterTipoReceitaDTO(medicacaoEntity, medicacao);

            medicacoes.add(medicacao);
        }

        return medicacoes;

    }

}
