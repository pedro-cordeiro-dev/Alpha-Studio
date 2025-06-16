package com.example.Alpha_Studio.Controller;

import com.example.Alpha_Studio.DTO.AgendamentoDTO;
import com.example.Alpha_Studio.Model.Agendamento;
import com.example.Alpha_Studio.Service.AgendamentoService;
import com.example.Alpha_Studio.Service.PrecoServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private PrecoServicoService precoService;

    @PostMapping("/agendamentos")
    public ResponseEntity<?> criarAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        try {
            Agendamento agendamento = agendamentoService.criarAgendamento(agendamentoDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Agendamento criado com sucesso!");
            response.put("agendamento", agendamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> listarTodosAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoService.listarTodosAgendamentos();
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
