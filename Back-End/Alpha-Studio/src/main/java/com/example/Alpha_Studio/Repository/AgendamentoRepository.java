package com.example.Alpha_Studio.Repository;

import com.example.Alpha_Studio.Model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByData(LocalDate data);

    boolean existsByDataAndHora(LocalDate data, LocalTime hora);

    List<Agendamento> findByTelefone(String telefone);

    List<Agendamento> findAll();

}
