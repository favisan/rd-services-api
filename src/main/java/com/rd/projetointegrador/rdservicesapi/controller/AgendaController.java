package com.rd.projetointegrador.rdservicesapi.controller;

import com.rd.projetointegrador.rdservicesapi.entity.AgPacienteEntity;
import com.rd.projetointegrador.rdservicesapi.entity.AgendaEntity;
import com.rd.projetointegrador.rdservicesapi.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.rd.projetointegrador.rdservicesapi.dto.Agenda;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

    @RestController
    public class AgendaController {

        @Autowired private AgendaService agendaService;

        //Grupo 2
        @GetMapping("/agenda/especialidade")
        public ResponseEntity getEspByAgenda() {

            return ResponseEntity.ok().body(agendaService.getEspByAgenda());
        }

        //Grupo 2
        @GetMapping("agenda/{idTipoConsulta}/{idEspecialidade}")
        public ResponseEntity getAgendaByEsp(@PathVariable("idTipoConsulta") BigInteger idC, @PathVariable("idEspecialidade") BigInteger idE){

            return ResponseEntity.ok().body(agendaService.getAgendaByEspecialidade(idE, idC));
        }

        //LISTA TODAS AS AGENDAS (Grupo 4)
        @GetMapping("/agenda")
        public ResponseEntity getAgendas() {

            List<AgendaEntity> agendas = agendaService.getAgendas();

            return ResponseEntity.status(HttpStatus.OK).body(agendas);
        }

        //LISTA AS AGENDAS FILTRANDO POR DIA (Grupo 4)
        @GetMapping("/agendas/{data}")
        public ResponseEntity getAgendasPorData(@PathVariable ("data") String diaDisponivel)  {

            try{
                Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(diaDisponivel);
                List<AgendaEntity> agendas = agendaService.getAgendasPorData(dt);

                return ResponseEntity.status(HttpStatus.OK).body(agendas);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendas.");
            }
        }

        //LISTA OS HORARIOS FILTRANDO POR DIA (Grupo 4)
        @GetMapping("/horarios/{data}")
        public ResponseEntity getHorarios(@PathVariable ("data") String diaDisponivel) {

            try {
                Date data = new SimpleDateFormat("yyyy-MM-dd").parse(diaDisponivel);
                List<Time> horarios = agendaService.getHorarios(data);

                return ResponseEntity.status(HttpStatus.OK).body(horarios);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de horários!");
            }
        }

        //Lista Agendamentos com status agendada e diponibilidade 2 por data e medico(Grupo 4)
        @GetMapping("/agendamentos/{data}/{idMedico}")
        public ResponseEntity getAgendamentosPorData(@PathVariable("data") String dataAgendada, @PathVariable ("idMedico") BigInteger idMedico) {

            try {
                Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAgendada);

                List<AgPacienteEntity> agendamentos = agendaService.getAgendamentosPorAgenda(data, idMedico);

                return ResponseEntity.status(HttpStatus.OK).body(agendamentos);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendamentos!");
            }
        }

        //Cadastra uma lista de agendas (Grupo 4)
        @PostMapping("/agendas/{data}")
        public ResponseEntity cadastrarAgendasPorDia(@PathVariable ("data") String data, @RequestBody List<Agenda> agendas) throws ParseException {

            try {
                Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(data);

                return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgendaPorDia(dt, agendas));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir lista de agendas!");
            }
        }

    }

