package com.rd.projetointegrador.rdservicesapi.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Status;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
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

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private LojaService lojaService;


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

    public List<String> getAgendamentosIndisponiveis(BigInteger id, String data) throws ParseException {

        System.out.println("DATA: "+data);

        String from = data + " 00:00:00";
        String to = data + " 23:59:59";

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data1 = formato.parse(from);
        Date data2 = formato.parse(to);

        LojaEntity loja = lojaRepository.findById(id).get();
        StatusEntity status = statusRepository.findById(BigInteger.valueOf(3l)).get(); //Status = Agendada

        List<AgServicoEntity> ag =  repository.findByIdLojaAndDtDataHoraBetweenAndIdStatus(loja,data1, data2, status);

        List<String> indisponiveis = getDatasString(ag);

        return indisponiveis;
    }

    public List<String> getDatasString(List<AgServicoEntity> agendamentos){

        List<String> datas = new ArrayList<>();
        for (AgServicoEntity ag: agendamentos){
            String dt = ag.getDtDataHora().toString().split(" ")[1];
            String dt1 = dt.split(":")[0];
            Integer d= Integer.parseInt(dt1);
            d +=3;
            dt = String.valueOf(d)+":"+ dt.split(":")[1] ;
            datas.add(dt);
        }
        return datas;
    }

    public List<AgServico> getAgendamentosUsuario(BigInteger id){

        List<PedidoEntity> pedidos = pedidoRespository.findByIdPaciente(id);
        List<AgServicoEntity> agendamentos = new ArrayList<>();

        //pegar todos os agendamentos do usuário
        for(PedidoEntity p: pedidos){
            for (AgServicoEntity a: p.getAgendamentos()){
                agendamentos.add(a);
            }
        }

        //transformar em agendamentos DTO
        List<AgServico> agDTO = conversaoAgEntityParaDTO(agendamentos);

        return agDTO;
    }

    public List<AgServico> conversaoAgEntityParaDTO(List<AgServicoEntity> agendamentos){

        List<AgServico> agDTO = new ArrayList<>();

        for(AgServicoEntity ag: agendamentos){

            AgServico a = new AgServico();
            a.setIdAgendamento(ag.getIdAgendamento());
            a.setIdServico(ag.getIdServico().getId());
            a.setIdLoja(ag.getIdLoja().getIdLoja());

            a.setLoja(lojaService.conversaoLojaDTO(ag.getIdLoja()));
            a.setServico(servicoService.conversaoEntityParaDTO(ag.getIdServico()));

            int difData = ag.getDtDataHora().compareTo(java.util.Calendar.getInstance().getTime());
                System.out.println("VALOR DE I: "+difData+" + PARA O ID: "+a.getIdAgendamento());
                System.out.println("ID STATUS ANTES: "+ag.getIdStatus().getId());
            if (difData < 0 && ag.getIdStatus().getId() == BigInteger.valueOf(3L)) {
                a.setIdStatus(BigInteger.valueOf(1l));
            }else {
                a.setIdStatus(ag.getIdStatus().getId());
            }
            System.out.println("ID STATUS DEPOIS: "+a.getIdStatus());
            a.setDtDataHora(ag.getDtDataHora());
            a.setIdPedido(ag.getPedido().getIdPedido());

            agDTO.add(a);
        }
        return agDTO;
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
