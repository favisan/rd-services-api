package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Status;
import com.rd.projetointegrador.rdservicesapi.entity.AgServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PedidoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.StatusEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgServicoRespository;
import com.rd.projetointegrador.rdservicesapi.repository.PedidoRepository;
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
    } /*Retorna todos os pedidos do usuário com id "id" e seus respectivos agendamentos*/

   public Map<Status, List<AgServico>> getAgendamentos(BigInteger id){

        List<PedidoEntity> pedidos = pedidoRespository.findByIdPaciente(id);
        Map<StatusEntity, List<AgServicoEntity>> mapAgendamentos = new HashMap<>();

       for(PedidoEntity p: pedidos){

            List<AgServicoEntity> agendamentos = p.getAgendamentos();

            for (AgServicoEntity a: agendamentos){
                StatusEntity statusEntity = a.getIdStatus();
                List<AgServicoEntity> listaAgendamentos;
                if(mapAgendamentos.containsKey(statusEntity)){
                    listaAgendamentos = mapAgendamentos.get(a.getIdStatus());
                }else{
                    listaAgendamentos = new ArrayList<AgServicoEntity>();
                }
                listaAgendamentos.add(a);
                mapAgendamentos.put(statusEntity, listaAgendamentos);
            }
        }//MapAgendamentos por paciente e status preenchido

       //Converter Map de Entity para DTO
       Map<Status, List<AgServico>> mapAgendamentosDTO = new HashMap<>();

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
   } /*Retorna os agendamentos do paciente com id "id" por status (Agendado, Cancelado ou Relizado)*/
}
