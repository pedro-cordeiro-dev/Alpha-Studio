package com.example.Alpha_Studio.Controller;

import com.example.Alpha_Studio.Model.PrecoServico;
import com.example.Alpha_Studio.Repository.PrecoServicoRepository;
import com.example.Alpha_Studio.Service.PrecoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/precos")
public class PrecoServicoController {

    @Autowired
    private PrecoServicoService service;
    @Autowired
    private PrecoServicoRepository precoServicoRepository;

    @GetMapping()
    public ResponseEntity<BigDecimal> obterPrecos(
            @RequestParam String tipoVeiculo,
            @RequestParam String servico) {

        try {
            BigDecimal valor = service.buscarValor(tipoVeiculo, servico);
            return ResponseEntity.ok(valor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PrecoServico>> listarTodos() {
        List<PrecoServico> precos = service.listarTodos();
        return ResponseEntity.ok(precos);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PrecoServico> atualizarPrecos(
            @PathVariable Long id,
            @RequestBody PrecoServico precoAtualizado) {

        PrecoServico atualizado = service.atualizarPrecos(id, precoAtualizado);
        return ResponseEntity.ok(atualizado);
    }
}
