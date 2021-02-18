package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Status;
import com.rd.projetointegrador.rdservicesapi.entity.AgServicoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.LojaEntity;
import com.rd.projetointegrador.rdservicesapi.entity.PedidoEntity;
import com.rd.projetointegrador.rdservicesapi.entity.StatusEntity;
import com.rd.projetointegrador.rdservicesapi.repository.AgServicoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.LojaRepository;
import com.rd.projetointegrador.rdservicesapi.repository.PedidoRepository;
import com.rd.projetointegrador.rdservicesapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigInteger;
import java.util.*;

@Service
public class AgServicoService {

    @Autowired
    private AgServicoRepository repository;

    @Autowired
    private PedidoRepository pedidoRespository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private StatusRepository statusRepository;


    @Transactional
    public String cancelarAgendamento(BigInteger id){
        StatusEntity statusEntity = statusRepository.findById(BigInteger.valueOf(2l)).get();
        int result = repository.updateIdStatus(id,statusEntity);

        if (result != 1)
            return "Não foi possível cancelar o agendamento";
        return "Agendamento cancelado com sucesso";
    }

    public List<PedidoEntity> getPedidos(BigInteger id){

        return pedidoRespository.findByIdPaciente(id);
    } /*Retorna todos os pedidos do usuário com id "id" e seus respectivos agendamentos*/


    public List<AgServicoEntity> getAgendamentosPorLoja(BigInteger id) throws ParseException {

        String from = "2021-02-02 00:00:01";
        String to = "2021-02-02 23:59:59";

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data1 = formato.parse(from);
        Date data2 = formato.parse(to);

        LojaEntity loja = lojaRepository.findById(id).get();
        StatusEntity status = statusRepository.findById(BigInteger.valueOf(3l)).get(); //Status = Agendada

        return repository.findByIdLojaAndDtDataHoraBetweenAndIdStatus(loja,data1, data2, status);
    }


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
