package com.example.Alpha_Studio.config;

import com.example.Alpha_Studio.Model.PrecoServico;
import com.example.Alpha_Studio.Repository.PrecoServicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class PrecoConfig {


    @Bean
    CommandLineRunner commandLineRunner(PrecoServicoRepository repository) {
        return args -> {

            inserirSeNaoExistir(repository, "hatch", "basica", BigDecimal.valueOf(30.00));
            inserirSeNaoExistir(repository, "hatch", "intermediaria", BigDecimal.valueOf(50.00));
            inserirSeNaoExistir(repository, "hatch", "premium", BigDecimal.valueOf(80.00));

            inserirSeNaoExistir(repository, "sedan", "basica", BigDecimal.valueOf(40.00));
            inserirSeNaoExistir(repository, "sedan", "intermediaria", BigDecimal.valueOf(60.00));
            inserirSeNaoExistir(repository, "sedan", "premium", BigDecimal.valueOf(90.00));

            inserirSeNaoExistir(repository, "suv", "basica", BigDecimal.valueOf(50.00));
            inserirSeNaoExistir(repository, "suv", "intermediaria", BigDecimal.valueOf(70.00));
            inserirSeNaoExistir(repository, "suv", "premium", BigDecimal.valueOf(100.00));

            inserirSeNaoExistir(repository, "pickup", "basica", BigDecimal.valueOf(60.00));
            inserirSeNaoExistir(repository, "pickup", "intermediaria", BigDecimal.valueOf(80.00));
            inserirSeNaoExistir(repository, "pickup", "premium", BigDecimal.valueOf(120.00));
        };
    }

    private void inserirSeNaoExistir(PrecoServicoRepository repository, String tipo, String servico, BigDecimal valor) {
        boolean existe = repository.existsByTipoVeiculoAndServico(tipo, servico);
        if (!existe) {
            repository.save(new PrecoServico(null, tipo, servico, valor));
        }
    }

}

