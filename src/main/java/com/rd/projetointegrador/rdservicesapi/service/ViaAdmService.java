package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.ViaAdm;
import com.rd.projetointegrador.rdservicesapi.entity.ViaAdmEntity;
import com.rd.projetointegrador.rdservicesapi.repository.ViaAdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaAdmService {

    //Repository
    @Autowired
    private ViaAdmRepository viaAdmRepository;

    //Listar todas as vias de adm
    public List<ViaAdm> listarViasAdm() {

        //Buscando Toda A Lista de Entity ViaAdm
        List<ViaAdmEntity> viasAdmEntity = viaAdmRepository.findAll();

        //Convertendo a Lista Entity ViaAdm para Lista DTO
        List<ViaAdm> viasAdm = new ArrayList<>();
        viasAdm = converterViasAdmDTO(viasAdmEntity, viasAdm);

        return viasAdm;

    }

    //Convertendo de Entity para DTO
    public ViaAdm converterViaAdmDTO(ViaAdmEntity viaAdmEntity, ViaAdm viaAdm) {

        //SETANDO OS VALORES NA DTO ViaAdm
        viaAdm.setIdViaAdm(viaAdmEntity.getIdViaAdm());
        viaAdm.setDsViaAdm(viaAdmEntity.getDsViaAdm());

        return viaAdm;
    }

    //Convertendo listaEntity para ListaDTO
    public List<ViaAdm> converterViasAdmDTO(List<ViaAdmEntity> viasAdmEntity, List<ViaAdm> viasAdm) {

        for(ViaAdmEntity viaAdmEntity : viasAdmEntity) {
            ViaAdm viaAdm = new ViaAdm();
            viaAdm = converterViaAdmDTO(viaAdmEntity, viaAdm);

            viasAdm.add(viaAdm);
        }

        return viasAdm;
    }

}
