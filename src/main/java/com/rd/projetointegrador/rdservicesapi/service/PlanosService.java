package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Genero;
import com.rd.projetointegrador.rdservicesapi.dto.Planos;
import com.rd.projetointegrador.rdservicesapi.dto.ServicoPlano;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.ContratoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PlanosRepository;
import com.rd.projetointegrador.rdservicesapi.repository.ServicoPlanoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanosService {
    //GRUPO1

    @Autowired private PlanosRepository repository;
    @Autowired private ServicoPlanoRepository servRepository;
    @Autowired private ServicoPlanoService servPlanService;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ContratoRepository contratoRepository;

    //MÉTODO: conversão de DTO para Entity
    public PlanosEntity conversaoPlanoEntity(Planos plano, PlanosEntity planoEntity) {
        planoEntity.setIdPlano(plano.getIdPlano());
        planoEntity.setNmPlano(plano.getNmPlano());
        planoEntity.setDsPlano(plano.getDsPlano());
        planoEntity.setVlPlano(plano.getVlPlano());

        return planoEntity;
    }
    //MÉTODO: conversão ListaDTO para ListaEntity
    public List<PlanosEntity> conversaoPlanosEntity( List<Planos> planos, List<PlanosEntity> planosEntities){
        for(Planos plano : planos){
            PlanosEntity planoEntity = new PlanosEntity();
            planoEntity = conversaoPlanoEntity(plano,planoEntity);
            planosEntities.add(planoEntity);
        }
        return planosEntities;
    }
    //MÉTODO: conversão de Entity para DTO
    public Planos conversaoPlanoDTO(PlanosEntity planoEntity, Planos plano) {
        plano.setIdPlano(planoEntity.getIdPlano());
        plano.setNmPlano(planoEntity.getNmPlano());
        plano.setDsPlano(planoEntity.getDsPlano());
        plano.setVlPlano(planoEntity.getVlPlano());

        return plano;
    }
    //MÉTODO: conversão listaEntity para ListaDTO
    public List<Planos> conversaoPlanosDTO(List<PlanosEntity> planosEntities, List<Planos> planos){
        for(PlanosEntity planoEntity : planosEntities){
            Planos plano = new Planos();
            plano = conversaoPlanoDTO(planoEntity,plano);
            planos.add(plano);
        }
        return planos;
    }

    //MÉTODOS RETORNANDO A ENTITY
    public PlanosEntity getPlano(BigInteger idPlano) {
        System.out.println("IdPlano: " + idPlano);
        Optional<PlanosEntity> optional = repository.findById(idPlano);
        return optional.get();
    }
    public List<PlanosEntity> getPlanos() {
        return repository.findAll();
    }

    //MÉTODOS RETORNANDO A DTO
    public List<Planos> getPlanosDTO() {
            List<PlanosEntity> planosEntities = getPlanos();
            List<Planos> planos = new ArrayList<>();
            planos = conversaoPlanosDTO(planosEntities, planos);

            return planos;
        }

    @Transactional
    public String cadastrarPlano(Planos plano){

        PlanosEntity planosEntity = new PlanosEntity();

        planosEntity.setNmPlano(plano.getNmPlano());
        planosEntity.setDsPlano(plano.getDsPlano());
        planosEntity.setVlPlano(plano.getVlPlano());
        //TODO: cadastrar serviços?

        repository.save(planosEntity);

        return "Plano cadastrado com sucesso";

    }

    @Transactional
    public String alterarPlano(Planos plano, BigInteger idPlano){

        PlanosEntity planoEntity = getPlano(idPlano);

        planoEntity.setNmPlano(plano.getNmPlano());
        planoEntity.setDsPlano(plano.getDsPlano());
        planoEntity.setVlPlano(plano.getVlPlano());

        List<ServicoPlanoEntity> listaServPlano = new ArrayList<>();
        for(ServicoPlano servico : plano.getServicos()){

            BigInteger idServicoPlano = servico.getIdServicoPlano();
            Optional<ServicoPlanoEntity> optional = servRepository.findById(idServicoPlano);
            ServicoPlanoEntity servPlanoEntity = optional.get();
            listaServPlano.add(servPlanoEntity);
        }

        planoEntity.setServicos(listaServPlano);

        planoEntity = repository.save(planoEntity);
        return "Alteração realizada com sucesso";
    }

    public String excluirPlano(BigInteger idPlano){
        repository.deleteById(idPlano);
        return "Exclusão de plano realizada com sucesso";

    }

    //GRUPO2

    public List<PlanosEntity> getPlanobyUsuario(BigInteger id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        List<ContratoEntity> contratos = contratoRepository.findByUsuario(usuario);
        List<PlanosEntity> listaPlanos = new ArrayList<>();
        for (ContratoEntity contratoEntity : contratos) {
            PlanosEntity plano = contratoEntity.getPlanosEntity();
            listaPlanos.add(plano);
        }
        return listaPlanos;
    }


}
