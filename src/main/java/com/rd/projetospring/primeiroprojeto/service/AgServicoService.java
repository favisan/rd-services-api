package com.rd.projetospring.primeiroprojeto.service;

import com.rd.projetospring.primeiroprojeto.dto.AgServico;
import com.rd.projetospring.primeiroprojeto.dto.Status;
import com.rd.projetospring.primeiroprojeto.entity.AgServicoEntity;
import com.rd.projetospring.primeiroprojeto.entity.PedidoEntity;
import com.rd.projetospring.primeiroprojeto.entity.StatusEntity;
import com.rd.projetospring.primeiroprojeto.repository.AgServicoRespository;
import com.rd.projetospring.primeiroprojeto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class AgServicoService {

    @Autowired
    private AgServicoRespository repository;

    @Autowired
    private PedidoRepository pedidoRespository;

    public List<PedidoEntity> getPedidos(BigInteger id){

        return pedidoRespository.findByIdPaciente(id);
    }



    

   public Map<Status, List<AgServico>> getAgendamentos(BigInteger id){

        List<PedidoEntity> pedidos = pedidoRespository.findByIdPaciente(id);
        Map<StatusEntity, List<AgServicoEntity>> mapAgendamentos = new TreeMap<StatusEntity, List<AgServicoEntity>>();

       for(PedidoEntity p: pedidos){

            List<AgServicoEntity> agendamentos = p.getAgendamentos();

            for (AgServicoEntity a: agendamentos){
                StatusEntity statusEntity = a.getIdStatus();
                List<AgServicoEntity> listaAgendamentos;
                if(mapAgendamentos.containsKey(statusEntity.getNome())){
                    listaAgendamentos = mapAgendamentos.get(a.getIdStatus());
                }else{
                    listaAgendamentos = new ArrayList<AgServicoEntity>();
                }
                listaAgendamentos.add(a);
                mapAgendamentos.put(statusEntity, listaAgendamentos);
            }
        }//MapAgendamentos por paciente e status preenchido

       //Converter mapa de Entity para DTO

       Map<Status, List<AgServico>> mapAgendamentosDTO = new TreeMap<Status, List<AgServico>>();

       for(Map.Entry<StatusEntity, List<AgServicoEntity>> entrada : mapAgendamentos.entrySet()){

           List<AgServico> listaAgendamentos = new ArrayList<>();
           Status s = new Status();
           s.setId(entrada.getKey().getId());
           s.setStatus(entrada.getKey().getNome());

           for(AgServicoEntity ag: entrada.getValue()){
               AgServico a = new AgServico();
               a.setIdLoja(ag.getIdLoja().getIdLoja());
               a.setIdAgendamento(ag.getIdAgendamento());
               a.setIdServico(ag.getIdServico().getId());
               a.setDtDataHora(ag.getDtDataHora());
               a.setIdPedido(ag.getPedido().getIdPedido());
               a.setIdStatus(s.getId());

               listaAgendamentos.add(a);
           }
           mapAgendamentosDTO.put(s,listaAgendamentos);
       }
        return mapAgendamentosDTO;
   }
}
