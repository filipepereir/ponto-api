package com.ponto.api.entity.dto.security;

public class UsuarioLoginDTO {

	private Integer id;
	private String email;
	private String senha;
	private String token;

	public UsuarioLoginDTO(String token) {
		this.token = token;
	}

	public UsuarioLoginDTO(Integer id, String email, String senha, String token) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
