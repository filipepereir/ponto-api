package com.ponto.api.entity;


import com.ponto.api.entity.dto.UsuarioDTO;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    public Usuario(UsuarioDTO usuario) {
        this.codigo = usuario.getCodigo();
        this.nome = usuario.getNome();
    }

    public Usuario() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer codigo;
    @Column(name = "DS_NOME")
    private String nome;
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA")
    private String senha;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
