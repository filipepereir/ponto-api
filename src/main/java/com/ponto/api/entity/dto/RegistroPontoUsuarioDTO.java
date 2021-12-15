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

public class RegistroPontoUsuarioDTO {

    public RegistroPontoUsuarioDTO(LocalDateTime dataHoraRegistro, LocalDate dataBatida, StatusRegistroPonto status, String mensagem, String nomeUsuario, Integer codigoUsuario) {
        this.dataHoraRegistro = dataHoraRegistro;
        this.dataBatida = dataBatida;
        this.status = status;
        this.mensagem = mensagem;
        this.nomeUsuario = nomeUsuario;
        this.codigoUsuario = codigoUsuario;
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

    private String nomeUsuario;

    private Integer codigoUsuario;

    public RegistroPontoUsuarioDTO(RegistroPonto registroPonto) {

        this.dataHoraRegistro = registroPonto.getDataHoraBatida();
        this.dataBatida = registroPonto.getDataBatida();
        this.status = registroPonto.getStatus();
        this.mensagem = registroPonto.getMensagem();
        this.nomeUsuario = registroPonto.getUsuario().getNome();
        this.codigoUsuario = registroPonto.getUsuario().getCodigo();
    }

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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
