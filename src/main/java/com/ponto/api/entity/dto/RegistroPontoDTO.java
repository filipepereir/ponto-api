package com.ponto.api.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ponto.api.entity.RegistroPonto;
import com.ponto.api.enums.StatusRegistroPonto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroPontoDTO {

    public RegistroPontoDTO(RegistroPonto registro) {
        this.dataHoraRegistro = registro.getDataHoraBatida();
        this.dataBatida = registro.getDataBatida();
        this.status = registro.getStatus();
        this.mensagem = registro.getMensagem();

    }

    public RegistroPontoDTO() {

    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraRegistro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataBatida;
    private StatusRegistroPonto status;
    private String mensagem;

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public LocalDate getDataBatida() {
        return dataBatida;
    }

    public void setDataBatida(LocalDate dataBatida) {
        this.dataBatida = dataBatida;
    }

    public StatusRegistroPonto getStatus() {
        return status;
    }

    public void setStatus(StatusRegistroPonto status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
