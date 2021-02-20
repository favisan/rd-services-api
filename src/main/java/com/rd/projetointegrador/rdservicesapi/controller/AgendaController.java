package com.rd.projetointegrador.rdservicesapi.controller;
import com.rd.projetointegrador.rdservicesapi.dto.AgendaOutput;
import com.rd.projetointegrador.rdservicesapi.dto.AgendamentoOutput;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AgendaController {

    @Autowired private AgendaService agendaService;

    //Grupo2 - Consulta às agendas médicas disponíveis filtradas por especialidade médica e por tipo de consulta (presencial ou online)
    @GetMapping("agenda/{idTipoConsulta}/{idEspecialidade}")
    public ResponseEntity getAgendaByEsp(@PathVariable("idTipoConsulta") BigInteger idC, @PathVariable("idEspecialidade") BigInteger idE) {
        return ResponseEntity.ok().body(agendaService.getAgendaByEspecialidade(idE, idC));
    }

    //Grupo2 - Mudar a disponibilidade da Agenda Médica para agendada
    @GetMapping("agenda/disponibilidade/{idAgPaciente}")
    public ResponseEntity mudarDisponibilidade(@PathVariable("idAgPaciente") BigInteger id) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.mudarDisponibilidadeParaAgendada(id));
    }

    //Grupo2 - Filtra as agendas disponiveis por data
    @GetMapping("agenda/filtro/{agendas}/{data}")
    public ResponseEntity filtrarAgendasDisp (@PathVariable("agendas") List<Agenda> agendas, @PathVariable("data") Date data) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.filtrarAgendasDispPorData(agendas, data));
    }

    //LISTA TODAS AS AGENDAS (Grupo 4)
    @GetMapping("/agenda")
    public ResponseEntity getAgendas() {
        List<Agenda> agendas = agendaService.getAgendas();
        return ResponseEntity.status(HttpStatus.OK).body(agendas);
    }

    //LISTA AS AGENDAS FILTRANDO POR DIA (Grupo 4)
    @GetMapping("/agendas/{data}")
    public ResponseEntity getAgendasPorData(@PathVariable("data") String diaDisponivel) {
        try {
            Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(diaDisponivel);
            List<AgendaEntity> agendas = agendaService.getAgendasPorData(dt);
            return ResponseEntity.status(HttpStatus.OK).body(agendas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendas.");
        }
    }

    //LISTA OS HORARIOS FILTRANDO POR DIA (Grupo 4)
    @GetMapping("/horarios/{data}/{idMedico}")
    public ResponseEntity getHorarios(@PathVariable("data") String diaDisponivel, @PathVariable("idMedico") BigInteger idMedico) {
        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(diaDisponivel);
            List<AgendaOutput> horarios = agendaService.getHorarios(data, idMedico);
            return ResponseEntity.status(HttpStatus.OK).body(horarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de horários!");
        }
    }

    //Lista Agendamentos com status agendada e diponibilidade 2 por data e medico(Grupo 4)
    @GetMapping("/agendamentos/{data}/{idMedico}")
    public ResponseEntity getAgendamentosPorData(@PathVariable("data") String dataAgendada, @PathVariable("idMedico") BigInteger idMedico) {
        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAgendada);
            List<AgendamentoOutput> agendamentos = agendaService.getAgendamentosPorAgenda(data, idMedico);
            return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar lista de agendamentos!");
        }
    }

    //Cadastra uma lista de agendas (Grupo 4)
    @PostMapping("/agendas/{data}")
    public ResponseEntity cadastrarAgendasPorDia(@PathVariable("data") String data, @RequestBody List<AgendaOutput> agendas) throws ParseException {
        try {
            Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.cadastrarAgendaPorDia(dt, agendas));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir lista de agendas!");
        }
    }

    //Cancelar consulta agendada
    @PutMapping("/agPaciente/status/{idAgPaciente}")
    public ResponseEntity cancelarConsulta(@PathVariable("idAgPaciente") BigInteger idAgPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.alterarStatusAgPaciente(idAgPaciente));
    }
}