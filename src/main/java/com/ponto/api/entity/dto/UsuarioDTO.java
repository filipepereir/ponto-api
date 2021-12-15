package com.ponto.api.entity.dto;

import com.ponto.api.entity.Usuario;

public class UsuarioDTO {

	public UsuarioDTO(Usuario usuario) {
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
	}

	public UsuarioDTO(Integer codigo, String nome, String email) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
	}

	private Integer codigo;
	private String nome;
	private String email;

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

}
