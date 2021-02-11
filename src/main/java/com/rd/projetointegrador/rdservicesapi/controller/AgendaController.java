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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @GetMapping("/agendas")
    public ResponseEntity getAgendasPorData(@RequestParam ("data") String diaDisponivel)  {

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
    @GetMapping("/horarios")
    public ResponseEntity getHorarios(@RequestParam ("data") String diaDisponivel) {

        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(diaDisponivel);
            List<Time> horarios = agendaService.getHorarios(data);

            return ResponseEntity.status(HttpStatus.OK).body(horarios);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de horários!");
        }
    }

    //Lista AgPaciente por data (Grupo 4)
    @GetMapping("/agendamentos")
    public ResponseEntity getAgendamentosPorData(@RequestParam("data") String dtSolicitacao) {

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime data = LocalDateTime.parse(dtSolicitacao, formato);

            List<AgPacienteEntity> agendamentos = agendaService.getAgendamentosPorData(data);

            return ResponseEntity.status(HttpStatus.OK).body(agendamentos);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendamentos!");
        }
    }

    //Cadastra uma agenda (Grupo 4)
    @PostMapping("/agenda")
    public ResponseEntity cadastrarAgenda(@RequestBody Agenda agenda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgenda(agenda));
    }

    //Cadastra uma agenda (Grupo 4)
    @PostMapping("/agendas")
    public ResponseEntity cadastrarAgendaPorDia(@RequestParam ("data") String data, @RequestBody List<Agenda> agendas) throws ParseException {

        Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgendaPorDia(dt, agendas));
    }

    //Exclui lista de agendas (Grupo 4)
    @DeleteMapping("/agenda")
    public ResponseEntity excluirAgendas(@RequestBody List<Agenda> agendas) {

        return ResponseEntity.status(HttpStatus.OK).body(agendaService.excluirAgendas(agendas));
    }
}
