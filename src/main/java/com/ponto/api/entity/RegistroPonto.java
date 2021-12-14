package com.ponto.api.entity;

import com.ponto.api.enums.StatusRegistroPonto;
import com.ponto.api.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "REGISTRO_PONTO")
public class RegistroPonto {

    public RegistroPonto() {
    }

    public RegistroPonto(Usuario usuario) {
        this.dataHoraBatida = DateUtils.dataHoraAtual();
        this.dataBatida = DateUtils.dataAtual();
        this.usuario = usuario;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer codigo;

    @Column(name = "TP_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusRegistroPonto status;

    @Column(name = "DH_DATA_HORA_BATIDA")
    private LocalDateTime dataHoraBatida;

    @Column(name = "DT_DATA_BATIDA")
    private LocalDate dataBatida;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

    @Column(name = "DS_MENSAGEM")
    private String mensagem;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public StatusRegistroPonto getStatus() {
        return status;
    }

    public void setStatus(StatusRegistroPonto status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraBatida() {
        return dataHoraBatida;
    }

    public void setDataHoraBatida(LocalDateTime dataHoraBatida) {
        this.dataHoraBatida = dataHoraBatida;
    }

    public LocalDate getDataBatida() {
        return dataBatida;
    }

    public void setDataBatida(LocalDate dataBatida) {
        this.dataBatida = dataBatida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
