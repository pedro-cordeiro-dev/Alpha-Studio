package com.example.Alpha_Studio.Service;

import com.example.Alpha_Studio.DTO.AgendamentoDTO;
import com.example.Alpha_Studio.Model.Agendamento;
import com.example.Alpha_Studio.Repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PrecoServicoService precoService;

    private static final DateTimeFormatter Dateformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter Timeformatter = DateTimeFormatter.ofPattern("HH:mm");

    public Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO) {


        validarDadosAgendamento(agendamentoDTO);

        LocalDate dataAgendamento = LocalDate.parse(agendamentoDTO.getData());
        LocalTime horaAgendamento = LocalTime.parse(agendamentoDTO.getHora());

            if(dataAgendamento.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Data incorreta");
            }

        validarHorarioFuncionamento(dataAgendamento, horaAgendamento);

        if(agendamentoRepository.existsByDataAndHora(dataAgendamento, horaAgendamento)) {

            throw new IllegalArgumentException("data e hora ja ocupada");

        }

        BigDecimal valor = precoService.buscarValor(agendamentoDTO.getTipoVeiculo(), agendamentoDTO.getServico());

        Agendamento agendamento = new Agendamento();
        agendamento.setNome(agendamentoDTO.getNome());
        agendamento.setTelefone(agendamentoDTO.getTelefone());
        agendamento.setMarca(agendamentoDTO.getMarca());
        agendamento.setModelo(agendamentoDTO.getModelo());
        agendamento.setCor(agendamentoDTO.getCor());
        agendamento.setTipoVeiculo(agendamentoDTO.getTipoVeiculo().toUpperCase());
        agendamento.setServico(agendamentoDTO.getServico().toUpperCase());
        agendamento.setData(dataAgendamento);
        agendamento.setHora(horaAgendamento);
        agendamento.setValor(valor);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodosAgendamentos(){
        return agendamentoRepository.findAll();
    }

    private void validarDadosAgendamento(AgendamentoDTO agendamentoDTO) {

        if(agendamentoDTO.getData() == null || agendamentoDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é Obrigatorio");
        }

        if(agendamentoDTO.getTelefone() == null || agendamentoDTO.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é Obrigatorio");
        }

        if(agendamentoDTO.getMarca() == null || agendamentoDTO.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("Marca é Obrigatorio");
        }
        if(agendamentoDTO.getModelo() == null || agendamentoDTO.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo é Obrigatorio");
        }
        if(agendamentoDTO.getCor() == null || agendamentoDTO.getCor().trim().isEmpty()) {
            throw new IllegalArgumentException("Cor é Obrigatorio");
        }
        if (agendamentoDTO.getTipoVeiculo() == null || agendamentoDTO.getTipoVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de veículo inválido");
        }
        if (agendamentoDTO.getServico() == null || agendamentoDTO.getServico().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de serviço inválido");
        }
    }

    private LocalDate parseData(String data) {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(data, Dateformatter);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Data incorreta");
            }
        }
    }
    private LocalTime parseHora(String hora) {
        try{
            return LocalTime.parse(hora, Timeformatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Hora incorreta");
        }
    }
    private void validarHorarioFuncionamento(LocalDate dataAgendamento, LocalTime horaAgendamento) {
        if(dataAgendamento.getDayOfWeek().getValue() == 7) {
            throw new IllegalArgumentException("Horário de funcionamento: Segunda a Sábado, 08:00 às 17:00");
        }
        LocalTime abertura = LocalTime.of(8, 0);
        LocalTime fechamento = LocalTime.of(17, 0);

        if (horaAgendamento.isBefore(abertura) || horaAgendamento.isAfter(fechamento)) {
            throw new IllegalArgumentException("Horário de funcionamento: Segunda a Sábado, 08:00 às 17:00");
        }
    }

}
