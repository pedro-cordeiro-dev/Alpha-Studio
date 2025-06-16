package com.example.Alpha_Studio.Repository;

import com.example.Alpha_Studio.Model.PrecoServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrecoServicoRepository extends JpaRepository<PrecoServico, Long> {
    PrecoServico findByTipoVeiculoAndServico(String tipoVeiculo, String servico);

    boolean existsByTipoVeiculoAndServico(String tipoVeiculo, String servico);


}
