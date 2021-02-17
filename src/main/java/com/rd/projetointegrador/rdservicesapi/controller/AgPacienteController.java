package com.rd.projetointegrador.rdservicesapi.controller;
import com.rd.projetointegrador.rdservicesapi.dto.CadastroAgPaciente;
import com.rd.projetointegrador.rdservicesapi.repository.UsuarioRepository;
import com.rd.projetointegrador.rdservicesapi.service.AgPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
public class AgPacienteController {

    @Autowired private AgPacienteService service;
    @Autowired private UsuarioRepository usuarioRepository;

    //Grupo2 - Consultar agenda de paciente pela id da agPaciente
    @GetMapping("/agPacientePorId/{idAgPaciente}")
    public ResponseEntity listarAgPacienteporId(@PathVariable("idAgPaciente") BigInteger idAgPaciente){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAgPacientePorId(idAgPaciente));
    }

     //Grupo2 - Consultar agendas de paciente filtradas pela Id do Usuario
    @GetMapping("/agPaciente/{idUsuario}")
    public ResponseEntity listarAgPaciente(@PathVariable("idUsuario") BigInteger idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAgPaciente(usuarioRepository.findById(idUsuario)));
    }

    //Grupo2 - Alterar a disponibilidade da agenda médica para disponível quando o paciente cancela a consulta
    @GetMapping ("/agPaciente/mudar-status/{idAgPaciente}")
    public ResponseEntity cancelarConsulta(@PathVariable("idAgPaciente") BigInteger idAgPaciente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cancelarAgPaciente(idAgPaciente));
    }

    //Grupo2 - Cadastrar nova Agenda do Paciente
    @PostMapping ("/agPaciente/cadastrar")
    public ResponseEntity cadastrarAgPaciente(@RequestBody CadastroAgPaciente cadastroAgPaciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.setAgPaciente(cadastroAgPaciente));
    }

}
