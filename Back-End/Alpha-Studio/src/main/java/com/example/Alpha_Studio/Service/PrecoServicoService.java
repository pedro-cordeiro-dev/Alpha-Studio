package com.example.Alpha_Studio.Service;

import com.example.Alpha_Studio.Model.PrecoServico;
import com.example.Alpha_Studio.Repository.PrecoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PrecoServicoService {

    @Autowired
    private PrecoServicoRepository Repository;

    public BigDecimal buscarValor(String tipoVeiculo, String servico) {
        PrecoServico preco = Repository.findByTipoVeiculoAndServico(tipoVeiculo, servico);

        if (preco == null) {
            throw new RuntimeException("Preço não encontrado para esse tipo de veículo e serviço");
        }

        return preco.getValor();
    }

    public List<PrecoServico> listarTodos() {
        return Repository.findAll();
    }


    public PrecoServico atualizarPrecos(Long id, PrecoServico precoAtualizado) {
        PrecoServico precoExistente = Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preço não encontrado"));

        precoExistente.setValor(precoAtualizado.getValor());

        return Repository.save(precoExistente);
    }




}